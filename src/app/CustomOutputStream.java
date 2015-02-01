package app;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;

/**
 * 
 * @author tomasz.mista
 * An output stream that writes its output to a javax.swing.JTextArea control.
 */

public class CustomOutputStream extends OutputStream {
	
    private JTextArea textControl;

    public CustomOutputStream( JTextArea control ) {
    	
        textControl = control;
    }

    
    public void write( int b ) throws IOException {
    	
        textControl.append( String.valueOf( ( char )b ) );
    }  
}
