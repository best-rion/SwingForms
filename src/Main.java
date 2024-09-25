    //import required classes and packages  
    import javax.swing.*;  
    import java.awt.*;  
    import java.awt.event.*;  
    import java.lang.Exception;
    import java.util.Scanner;

import databaseConnector.SignupForm;
import databaseConnector.UserInfo;
import databaseConnector.PasswordSecurity;
      
    
    
    //create CreateSignupForm class to create Signupform  
    //class extends JFrame to create a window where our component add  
    //class implements ActionListener to perform an action on button click  
    class CreateSignupForm extends JFrame implements ActionListener  
    {  
        //initialize button, panel, label, and text field  
        JButton b1;  
        JPanel newPanel;  
        JLabel first_nameLabel,last_nameLabel, emailLabel, passwordLabel;  
        final JTextField  textField1, textField2, textField3, textField4;  
          
        //calling constructor  
        CreateSignupForm()  
        {     
              
            //create label for first_name   
            first_nameLabel = new JLabel();  
            first_nameLabel.setText("First Name");      //set label value for textField1  
              
            //create text field to get username from the user  
            textField1 = new JTextField(15);    //set length of the text  
      
            //create label for password  
            last_nameLabel = new JLabel();  
            last_nameLabel.setText("Last Name");      //set label value for textField2  
              
            //create text field to get password from the user  
            textField2 = new JTextField(15);    //set length for the password  
            
            //create label for password  
            emailLabel = new JLabel();  
            emailLabel.setText("Email");      //set label value for textField2  
              
            //create text field to get password from the user  
            textField3 = new JTextField(15);    //set length for the password  
            
          //create label for password  
            passwordLabel = new JLabel();  
            passwordLabel.setText("Password");      //set label value for textField2  
              
            //create text field to get password from the user  
            textField4 = new JPasswordField(15);    //set length for the password  
              
            //create submit button  
            b1 = new JButton("SUBMIT"); //set label to button  
              
            //create panel to put form elements  
            newPanel = new JPanel(new GridLayout(5,2, 10, 20));  
            newPanel.add(first_nameLabel);    //set first_name label to panel  
            newPanel.add(textField1);   //set text field to panel  
            newPanel.add(last_nameLabel);    //set last_name label to panel   
            newPanel.add(textField2);   //set text field to panel  
            newPanel.add(emailLabel);    //set last_name label to panel   
            newPanel.add(textField3);   //set text field to panel  
            newPanel.add(passwordLabel);    //set last_name label to panel  
            newPanel.add(textField4);   //set text field to panel  
            newPanel.add(b1);           //set button to panel  
              
            //set border to panel   
            add(newPanel, BorderLayout.CENTER);  
              
            //perform action on button click   
            b1.addActionListener(this);     //add action listener to button  
            setTitle("SIGNUP FORM");         //set title to the login form  
        }  
          
        //define abstract method actionPerformed() which will be called on button click   
        public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter  
        {
        	SignupForm.setFirstName(textField1.getText());
        	SignupForm.setLastName(textField2.getText());
        	SignupForm.setEmail(textField3.getText());
        	String encryptedPassword = PasswordSecurity.encrypt( textField4.getText() );
        	SignupForm.setPassword( encryptedPassword );
        	
        	SignupForm.saveToDB();
              
            //check whether the credentials are authentic or not  
            if (true) {  //if authentic, navigate user to a new page  
                  
                //create instance of the NewPage  
                NewPage page = new NewPage();  
                  
                //make page visible to the user  
                page.setVisible(true);  
                  
                //create a welcome label and set it to the new page  
                JLabel wel_label = new JLabel("Thank You, "+SignupForm.getFirstName()+" for joining us");  
                page.getContentPane().add(wel_label);  
            }
        }  
    }  
    
    
    
    //create CreateLoginForm class to create login form  
    //class extends JFrame to create a window where our component add  
    //class implements ActionListener to perform an action on button click  
    class CreateLoginForm extends JFrame implements ActionListener 
    {  
        //initialize button, panel, label, and text field  
        JButton b1;  
        JPanel newPanel;  
        JLabel userLabel, passLabel;  
        final JTextField  textField1, textField2;  
          
        //calling constructor  
        CreateLoginForm()  
        {     
              
            //create label for username   
            userLabel = new JLabel();  
            userLabel.setText("Email");      //set label value for textField1  
              
            //create text field to get username from the user  
            textField1 = new JTextField(15);    //set length of the text  
      
            //create label for password  
            passLabel = new JLabel();  
            passLabel.setText("Password");      //set label value for textField2  
              
            //create text field to get password from the user  
            textField2 = new JPasswordField(15);    //set length for the password  
              
            //create submit button  
            b1 = new JButton("SUBMIT"); //set label to button  
              
            //create panel to put form elements  
            newPanel = new JPanel(new GridLayout(3,2, 10, 20));  
            newPanel.add(userLabel);    //set username label to panel  
            newPanel.add(textField1);   //set text field to panel  
            newPanel.add(passLabel);    //set password label to panel  
            newPanel.add(textField2);   //set text field to panel  
            newPanel.add(b1);           //set button to panel  
              
            //set border to panel   
            add(newPanel, BorderLayout.CENTER);  
              
            //perform action on button click   
            b1.addActionListener(this);     //add action listener to button  
            setTitle("LOGIN FORM");         //set title to the login form  
        }  
          
        //define abstract method actionPerformed() which will be called on button click   
        public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter  
        {  
        	

        	
            String userValue = textField1.getText();        //get user entered username from the textField1  
            String passValue = textField2.getText();        //get user entered pasword from the textField2 
            
            
        	UserInfo.RetrieveInfoFromDB(userValue);
        	
        	String userEmail = UserInfo.getEmail();
        	String userPassword = UserInfo.getPassword();
              
            //check whether the credentials are authentic or not  
        	String decryptedPassword = PasswordSecurity.decrypt(userPassword);
            if (userValue.equals(userEmail) && passValue.equals( decryptedPassword )) {  //if authentic, navigate user to a new page  
                  
                //create instance of the NewPage  
                NewPage page = new NewPage();  
                  
                //make page visible to the user  
                page.setVisible(true);  
                  
                //create a welcome label and set it to the new page  
                JLabel wel_label = new JLabel("Welcome: "+UserInfo.getFirstName()+" "+UserInfo.getLastName());  
                page.getContentPane().add(wel_label);  
            }  
            else{  
                //show error message  
                System.out.println("Please enter valid username and password");  
            }  
        }  
    }  
    //create the main class  
    class Main  
    {  
        //main() method start  
        public static void main(String arg[])  
        {  
            try  
            {  
            	Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            	System.out.print("Type \'S\' for Signup, \'L\' for Login : ");
                String userInput = myObj.nextLine();  // Read user input
                
                if (userInput.equals("S"))
                {
                	//create instance of the CreateLoginForm  
                    CreateSignupForm form = new CreateSignupForm();  
                    form.setSize(300,250);  //set size of the frame  
                    form.setVisible(true);  //make form visible to the user  
                }
                else if (userInput.equals("L"))
                {
                    //create instance of the CreateLoginForm  
                    CreateLoginForm form = new CreateLoginForm();  
                    form.setSize(300,150);  //set size of the frame  
                    form.setVisible(true);  //make form visible to the user  
                }
            }  
            catch(Exception e)  
            {     
                //handle exception   
                JOptionPane.showMessageDialog(null, e.getMessage());  
            }  
        }  
    }  


