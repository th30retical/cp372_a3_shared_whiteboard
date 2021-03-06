import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.io.*;
import java.net.*;
 
 
//create a gui for the client 
public class SwingPaint {
 
  JButton clearBtn, connect, disconnect;
  JTextField IP, port;
  DrawArea drawArea;
  public Container content;
  public JFrame frame;
  
  ActionListener actionListener = new ActionListener() {


  public void actionPerformed(ActionEvent e) {
      if (e.getSource() == clearBtn) {
        drawArea.clear();
      }else if(e.getSource()==disconnect){
         drawArea.disconnect();
         frame.remove(drawArea);
         frame.revalidate();
         frame.repaint();
         
        disconnect.setEnabled(false);
        connect.setEnabled(true);
         
      }
      
      
      
    }
  };
 
  public static void main(String[] args) throws IOException{
    new SwingPaint().show();
  }
 
  public void show() throws IOException{
    // create main frame
    String ip,portString;
    frame = new JFrame("Swing Paint");
    content = frame.getContentPane();
    // set layout on content pane
    content.setLayout(new BorderLayout());
    // create draw area
 
    // create controls to apply colors and call clear feature
    JPanel controls = new JPanel();
 	controls.setLayout(new BorderLayout());
 	 
    clearBtn = new JButton("Clear Your Own Screen");
    
    clearBtn.addActionListener(actionListener);
    
 	
 	//add drawing area content to frame after connecting properly 
 	connect=new JButton("Connect");
 	connect.addActionListener(new ActionListener(){
 		
 		public void actionPerformed(ActionEvent e){
 			String ip=IP.getText();
		    String portString=port.getText();
		    try{
		    
		    	drawArea=new DrawArea(ip,portString);

		    	content.add(drawArea);
    	
                        
                        disconnect.setEnabled(true);
                        connect.setEnabled(false);

 			}catch(IOException r){
 				System.err.println("Error");
 			}
 			frame.revalidate();
 			frame.repaint();
 		}
 	});
 	
 	
 	
 	
 	
 	//disconnect button
 	disconnect=new JButton("Disconnect");
 	
 	disconnect.addActionListener(actionListener);
 	
 	IP=new JTextField("IP address");
 	
 	//adds on click clear textfield for IP textfield
 	IP.addMouseListener(new MouseAdapter(){
 		
 	    @Override
 	    public void mouseClicked(MouseEvent e){
 	    	if (IP.getText().contains("IP address")){
 	    		IP.setText("");
 	    	}
 	    }
 	});
 	
 	
 	
 	port=new JTextField("Port Number");
 	//adds on click clear textfield for port textfield
 	port.addMouseListener(new MouseAdapter(){
 		
 	    @Override
 	    public void mouseClicked(MouseEvent e){
 	    	if (port.getText().contains("Port Number")){
 	    		port.setText("");
 	    	}
 	    }
 	});
 	
 	
 	
 	
 	
 	connect.addActionListener(actionListener);
    // add to panel


    controls.add(clearBtn, BorderLayout.SOUTH);
    controls.add(port, BorderLayout.EAST);
    controls.add(IP, BorderLayout.CENTER);
    controls.add(disconnect, BorderLayout.WEST);
    controls.add(connect, BorderLayout.NORTH);
    // add to content pane
    content.add(controls, BorderLayout.NORTH);
    
    
    disconnect.setEnabled(false);
    
 
    frame.setSize(600, 600);
    // can close frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // show the swing paint result
    frame.setVisible(true);
 

  }
 
}
