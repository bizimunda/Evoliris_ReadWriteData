package evoliris.com.evoliris_readwritedata;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends ActionBarActivity {

    private Button btnWrite, btnRead, btnDelete;
    private EditText etMaintext;
    String text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWrite = (Button) findViewById(R.id.btn_main_write);
        btnRead = (Button) findViewById(R.id.btn_main_read);
        btnDelete = (Button) findViewById(R.id.btn_main_delete);
        etMaintext=(EditText)findViewById(R.id.et_main_text);


    }

    public void write(View v) {

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    text=etMaintext.getText().toString();
                    FileOutputStream fos = openFileOutput("myfile.dat", MODE_PRIVATE);
                    fos.write(text.getBytes());
                    if(fos!=null){
                        fos.close();
                    }
                } catch (IOException e) {
                    Log.e("write", e.getMessage());
                }

            }
        });
        Toast.makeText(MainActivity.this, "Text Written: "+text, Toast.LENGTH_SHORT).show();


    }

    public void read(View v) {

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    etMaintext.setText("");
                    FileInputStream fis = openFileInput("myfile.dat");
                    if(fis==null){
                        Toast.makeText(MainActivity.this,"File doest not exist ",Toast.LENGTH_SHORT).show();
                    } else {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                        StringBuilder out = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            out.append(line);
                        }
                        reader.close();
                        fis.close();

                        Toast.makeText(MainActivity.this,"Written text is: "+out.toString(),Toast.LENGTH_SHORT).show();
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Log.e("Read method",e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("Read method",e.getMessage());
                }
            }
        });

    }

    public void delete (View v){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File dir=getFilesDir();
                File file= new File(dir,"myfile.dat");
                boolean deleted=file.delete();
                Toast.makeText(MainActivity.this,"File deleted",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
