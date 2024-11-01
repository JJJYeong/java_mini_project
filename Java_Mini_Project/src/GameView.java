import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

import javax.swing.*;

import dao.GameDAO;
import model.ExampleDTO;
import model.LankingDTO;

public class GameView extends JFrame {
	private JTextField nameField;			// 닉네임 입력필드
	private JPanel startPan = new JPanel(); // 시작화면
	private JPanel gamePan = new JPanel();  // 게임화면
	private LocalDateTime sTime;	// 시작시간
	private long duration;			// 걸린 시간
	
	private Map<String, LankingDTO> lanking; // {닉네임 : 기록} (insert/update 판별용)
	private GameDAO dao = new GameDAO();
	
	public GameView() {
        startPan.setLayout(new BoxLayout(startPan, BoxLayout.Y_AXIS));
        
        // 입력창
        JPanel inputPan = new JPanel();
        JLabel label = new JLabel("닉네임 : ");
        label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        inputPan.add(label);
        nameField = new JTextField(10);
        nameField.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        inputPan.add(nameField);
        
        // 버튼
        JPanel buttonPan = new JPanel();
        JButton korButton = new JButton("한국어");
        JButton engButton = new JButton("영어");
        korButton.setPreferredSize(new Dimension(120, 60));
        engButton.setPreferredSize(new Dimension(120, 60));
        korButton.addActionListener(e -> startGame("kor"));
        engButton.addActionListener(e -> startGame("eng"));
        buttonPan.add(korButton);
        buttonPan.add(engButton);
        
        startPan.add(Box.createVerticalGlue());
        startPan.add(inputPan);
        startPan.add(buttonPan);
        startPan.add(Box.createVerticalGlue());
        
        add(startPan);
        
        setTitle("간단한 타자연습 게임");
        setSize(900, 300);
        setLocation(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    // 게임화면 로드
    public void startGame(String lan) {
        if(nameField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "닉네임은 필수입니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        gamePan.setLayout(new GridBagLayout());
        
        // 데이터 가져오기
        ExampleDTO example = dao.selectExample(lan);
        lanking = dao.selectLanking(example.getIdx());
        
        // 입력 파트
        JPanel inputPan = new JPanel();
        inputPan.setLayout(new GridBagLayout());
        JLabel exlabel = new JLabel(example.getContent());
        exlabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));

        // GridBagConstraints 설정
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 5, 0);
        inputPan.add(exlabel, gbc);

        // 입력 필드
        gbc.gridx = 0;
        gbc.gridy = 1; // 다음 행에 배치
        gbc.weightx = 1.0; // 가로 비율을 1로 설정하여 공간을 채우게 함
        gbc.fill = GridBagConstraints.HORIZONTAL; // 가로 방향으로 채우기
        gbc.insets = new Insets(0, 0, 15, 0); // 아래쪽 여백 추가
        JTextField inputField = new JTextField(55);
        inputPan.add(inputField, gbc);

        // 추가 레이블 및 버튼
        gbc.gridx = 0;
        gbc.gridy = 2; // 다음 행에 배치
        gbc.weightx = 0; // 가로 비율을 0으로 설정
        gbc.fill = GridBagConstraints.NONE; // 기본 설정
        gbc.insets = new Insets(0, 0, 30, 0); // 아래쪽 여백 추가
        JLabel infolabel = new JLabel("입력창 클릭하여 시작, 엔터로 종료");
        infolabel.setFont(new Font("맑은 고딕", Font.ITALIC, 10));
        infolabel.setForeground(Color.BLUE);
        inputPan.add(infolabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3; // 다음 행에 배치
        gbc.weightx = 0; // 가로 비율을 0으로 설정
        gbc.fill = GridBagConstraints.NONE; // 기본 설정
        gbc.insets = new Insets(0, 0, 10, 0); // 아래쪽 여백 추가
        JButton restartBtn = new JButton("다시하기");
        inputPan.add(restartBtn, gbc);
        
        // 입력 시작
        inputField.addFocusListener(new FocusAdapter()  {
            public void focusGained(FocusEvent e) {
            	sTime = LocalDateTime.now();
                System.out.println("Started at: " + sTime);
            }
        });
        // 입력 종료
        inputField.addActionListener(e -> {
        	inputField.setFocusable(false);
            
        	// 예시와 입력 글 비교
            if(!example.getContent().equals(inputField.getText())) {
            	JOptionPane.showMessageDialog(this, "땡! 틀렸습니다.", "실패", JOptionPane.ERROR_MESSAGE);
            	return;
            }
            
            // 걸린 시간 계산
            LocalDateTime eTime = LocalDateTime.now();
        	System.out.println("Enter pressed at: " + eTime);
        	duration = Duration.between(sTime, eTime).getSeconds();
        	System.out.println(duration);
        	
            JOptionPane.showMessageDialog(this, "맞췄습니다!", "성공", JOptionPane.INFORMATION_MESSAGE);
            dao.insertLanking(example.getIdx(), nameField.getText(), duration);
        });
        
        // 랭킹 파트
        JPanel lankPan = new JPanel();
        lankPan.setLayout(new BoxLayout(lankPan, BoxLayout.Y_AXIS));
        lankPan.setBackground(Color.WHITE);
        lankPan.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY), 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // 랭킹 정보 추가
        JLabel lankLabel = new JLabel("랭킹");
        lankLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lankPan.add(lankLabel);
        lankPan.add(Box.createVerticalStrut(10));

        for (LankingDTO lank : lanking.values()) {
            JLabel rankInfoLabel = new JLabel(lank.getNickname() + " : " + lank.getDuration() + "초");
            rankInfoLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
            lankPan.add(rankInfoLabel);
        }
        
        // GridBagConstraints 설정
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0; // inputPan 위치
        gbc2.gridy = 0; // 첫 번째 행
        gbc2.weightx = 1.0;
        gbc2.weighty = 1.0; // 비율로 공간 차지
        gbc2.anchor = GridBagConstraints.WEST;
        gbc2.insets = new Insets(20, 20, 20, 20); // 여백 추가
        gamePan.add(inputPan, gbc2);
        
        gbc2.gridx = 1; // 랭킹 패널 위치
        gbc2.weightx = 1.0;
        gbc2.fill = GridBagConstraints.BOTH; // 패널이 공간을 채우도록 설정
        gbc2.anchor = GridBagConstraints.NORTHWEST;
        gbc2.insets = new Insets(20, 20, 20, 20); // 여백 추가
        gamePan.add(lankPan, gbc2);
        
        // 화면 전환
        remove(startPan);
        add(gamePan);
        revalidate();
        repaint();
    }

	
	
	public static void main(String[] args) {
		new GameView();
	}

}
