package ec.edu.tecnologicoloja.contactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import ec.edu.tecnologicoloja.contactlist.adapter.ContactAdapter;
import ec.edu.tecnologicoloja.contactlist.manager.FirebaseContactManager;
import ec.edu.tecnologicoloja.contactlist.model.Contact;
import ec.edu.tecnologicoloja.contactlist.views.ListActivity;

public class FordeRegistro extends AppCompatActivity implements View.OnClickListener {

    private ContactAdapter contactAdapter;

    private TextView txtNombre;
    private TextView txtCiudad;
    private TextView txtTelefono;
    private TextView txtDescripcion;

    private TextView txtFoto;
    private Button btnGuardar, btnCancelar;
    DatabaseReference database;
    private List<Contact> mListContacts = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forde_registro);

        txtNombre = findViewById(R.id.textName);
        txtCiudad = findViewById(R.id.textCity);
        txtTelefono = findViewById(R.id.textPhone);
        txtDescripcion = findViewById(R.id.textDescripcion);



        btnGuardar = findViewById(R.id.btnguardar);
        btnCancelar= findViewById(R.id.btnCancelar);

        database = FirebaseDatabase.getInstance().getReference("Contacto");

        mListContacts = FirebaseContactManager.getInstance().getAllContacts();
    }

    public void guardarBD() {


        String nombre = txtNombre.getText().toString();
        String ciudad = txtCiudad.getText().toString();
        String telefono = txtTelefono.getText().toString();
        String Descripcion = txtDescripcion.getText().toString();


        Contact contact1 = new Contact();
        contact1.setName(nombre);
        contact1.setCity(ciudad);
        contact1.setPhone(telefono);
        contact1.setDescription(Descripcion);

        mListContacts.add(contact1);

        Toast.makeText(this, "Guardado en FireBase", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onClick(View v) {
        if(btnGuardar == v){
            guardarBD();
            Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();
            onBackPressed();

        }else{
            Toast.makeText(this, "No se pudo guardar", Toast.LENGTH_SHORT).show();
           /* Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
            onBackPressed();*/
        }
    }

    protected void onRestart(){
        super.onRestart();
        guardarBD();
        contactAdapter.notifyDataSetChanged();

    }



}
