import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Clientform extends JFrame implements ActionListener{
    
    JButton submit;     //global variables
    JButton reset;
    JTextField tname;
    JTextField tmail;
    JTextField tfeedback;
    Container cp;
    JFrame f;
    JRadioButton one;
    JRadioButton two;
    JRadioButton three;
    JRadioButton four;
    JRadioButton five;
    ButtonGroup rating;
    JComboBox type;
    String[] types= {"Pinata","Ice-Cake","Cheese-Cake","Fondant","3-tier"};
   
    public Clientform(){
        this.setTitle("Client Form");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(540, 500);
        cp= getContentPane();
        this.setLayout(null);
        this.cp.setBackground(new java.awt.Color(0xA6E3E9));

        ImageIcon icon= new ImageIcon("icon.png");
        this.setIconImage(icon.getImage());

        JLabel heading= new JLabel();
        heading.setText("FEEDBACK");
        heading.setFont(new Font("Arial", Font.BOLD, 20));
        heading.setBounds(200,0,440,45);
        this.cp.add(heading);


        JLabel lname= new JLabel();
        lname.setText("Name: ");
        lname.setFont(new Font("Calibri", Font.PLAIN, 18));
        lname.setBounds(50,60,80,28);
        this.cp.add(lname);

        tname = new JTextField();
        tname.setFont(new Font("Calibri", Font.PLAIN, 16));
        tname.setBounds(230, 60, 240, 28);
        this.cp.add(tname);

        JLabel lmail= new JLabel();
        lmail.setText("Email Address: ");
        lmail.setFont(new Font("Calibri", Font.PLAIN, 18));
        lmail.setBounds(50,110, 150, 28);
        this.cp.add(lmail);

        tmail = new JTextField();
        tmail.setFont(new Font("Calibri", Font.PLAIN, 16));
        tmail.setBounds(230, 105, 240, 28);
        this.cp.add(tmail);

	JLabel lage= new JLabel();
        lage.setText("Type: ");
        lage.setFont(new Font("Calibri", Font.PLAIN, 18));
        lage.setBounds(50,155,150,28);
        this.cp.add(lage);

        type= new JComboBox<>(types);
        type.setBounds(230,155,120,28);
        this.cp.add(type);

        JLabel lrating= new JLabel();
        lrating.setText("Rating: ");
        lrating.setFont(new Font("Calibri", Font.PLAIN, 18));
        lrating.setBounds(50,200,100,28);
        this.cp.add(lrating);


        one=new JRadioButton("1");
        two=new JRadioButton("2");
        three=new JRadioButton("3");
        four=new JRadioButton("4");
        five=new JRadioButton("5");

        one.setBounds(230, 200, 40, 28);
        two.setBounds(280, 200, 40, 28);
        three.setBounds(330, 200, 40, 28);
        four.setBounds(380, 200, 40, 28);
        five.setBounds(430, 200, 40, 28);
        five.setSelected(true);

        this.add(one);
        this.add(two);
        this.add(three);
        this.add(four);
        this.add(five);

        rating= new ButtonGroup();  //A buttongroup ensures only one of the 5 radio buttons are selected at a time
        rating.add(one);
        rating.add(two);
        rating.add(three);
        rating.add(four);
        rating.add(five);

        JLabel lfeedback= new JLabel();
        lfeedback.setText("Feedback (optional): ");
        lfeedback.setFont(new Font("Calibri", Font.PLAIN, 18));
        lfeedback.setBounds(50,250,170,28);
        this.cp.add(lfeedback);

        tfeedback = new JTextField();
        tfeedback.setFont(new Font("Calibri", Font.PLAIN, 16));
        tfeedback.setBounds(230, 250, 240, 84);
        this.cp.add(tfeedback);

        submit= new JButton("Submit");
        submit.setFont(new Font("Calibri", Font.PLAIN, 18));
        submit.setBounds(165, 355, 100, 28);
        submit.addActionListener(this);
        this.cp.add(submit);

        reset= new JButton("Reset");
        reset.setFont(new Font("Calibri", Font.PLAIN, 18));
        reset.setBounds(275, 355, 100, 28);
        reset.addActionListener(this);
        this.cp.add(reset);

        this.setVisible(true);
    }

    //@Override
    public void actionPerformed(ActionEvent e){
        boolean flag=false;
        String emailvalidation = "^[a-zA-Z0-9+_.-]+@(.+)$";  
        Pattern p= Pattern.compile(emailvalidation);
        Matcher mat= p.matcher(tmail.getText());
        f= new JFrame();

        if(e.getSource()==submit)
        {
            if((tname.getText().isEmpty()) || (tname.getText() == null))
            {
                JOptionPane.showMessageDialog(f, "Name cannot be empty");
            }
            else if((tmail.getText().isEmpty()) || (tmail.getText() == null))
            {
                JOptionPane.showMessageDialog(f, "Email cannot be empty");
            }
            else if(!mat.matches())
            {
                JOptionPane.showMessageDialog(f, "Please Enter a valid Email");
            }
            else
                flag=true; 
                
            if(flag)
            {
                int r;
                if(one.isSelected())
                    r=1;
                else if(two.isSelected())
                    r=2;
                else if(three.isSelected())
                    r=3;    
                else if(four.isSelected())
                    r=4;
                else
                    r=5;
                String s1= "Thank you for your valuable Feeedback!\n\nYour Responses:-\n";
                String s2= "Name: "+tname.getText()+"\nEmail: "+tmail.getText()+"\nAge group: "+"\nRating: "+r+"\nFeedback: "+tfeedback.getText();
                String disp=s1+s2;
                JOptionPane.showMessageDialog(f, disp);
		String driver="oracle.jdbc.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String user_name="system";
		String pwd="sakshi";
		int ch=0;

		try {
			Class.forName(driver);
			
			Connection con= DriverManager.getConnection(url,user_name,pwd);
		    	PreparedStatement ps = con.prepareStatement("INSERT INTO cakefeedback(name,email,type,rating,descr) VALUES (?,?,?,?,?)");
			ps.setString(1, (String) tname.getText());
			ps.setString(2, (String) tmail.getText());
			ps.setString(3, (String) type.getSelectedItem());
			ps.setInt(4, (int) r);
			ps.setString(5, (String) tfeedback.getText());
			int x = ps.executeUpdate();
			if(x > 0) {
				JOptionPane.showMessageDialog(null, "Order Details Saved.", null, JOptionPane.YES_OPTION);
				}
			else {
				JOptionPane.showMessageDialog(null, "Order Details Not Saved.", null, JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception e1) {
			System.out.println(e1);
		}
            }
            
        }

        else if( e.getSource()==reset){
            tname.setText(null);
            tmail.setText(null);
            tfeedback.setText(null);
            one.setSelected(false); 
            two.setSelected(false);   
            three.setSelected(false); 
            four.setSelected(false); 
            five.setSelected(true);       
        }

    }
public static void main(String args[]) throws Exception{
        Clientform cf= new Clientform();
    }
}
