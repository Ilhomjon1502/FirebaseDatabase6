package uz.ilhomjon.firebasedatabase6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import uz.ilhomjon.firebasedatabase6.adapter.UserAdapter
import uz.ilhomjon.firebasedatabase6.databinding.ActivityMainBinding
import uz.ilhomjon.firebasedatabase6.modles.User

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var firebaseFireStore:FirebaseFirestore
    lateinit var firebaseStorage: FirebaseStorage
    lateinit var reference: StorageReference
    lateinit var userAdapter:UserAdapter
    lateinit var list : ArrayList<User>

    var imageUrl:String? = null

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseFireStore = FirebaseFirestore.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        reference = firebaseStorage.getReference("photos")

        binding.btnSave.setOnClickListener {
            val user = User(binding.edtName.text.toString(),
                imageUrl
                )
            firebaseFireStore.collection("Codial6")
                .add(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "Yuklandi", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }

        firebaseFireStore.collection("Codial6")
            .get()
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    list = ArrayList()
                    val result = it.result
                    result?.forEach { queryDocumentSnapshot ->
                        val user = queryDocumentSnapshot.toObject(User::class.java)
                        list.add(user)
                    }
                    userAdapter = UserAdapter(list)
                    binding.rv.adapter = userAdapter
                }
            }

        binding.imageView.setOnClickListener {
            getImageContent.launch("image/*")
        }
    }

    private var getImageContent = registerForActivityResult(ActivityResultContracts.GetContent()){ uri->

        val m = System.currentTimeMillis()
        val uploadTask = reference.child(m.toString()).putFile(uri)

        uploadTask.addOnSuccessListener {
            val downloadUrl = it.metadata?.reference?.downloadUrl
            downloadUrl?.addOnSuccessListener {imageUri->
                imageUrl = imageUri.toString()
                binding.imageView.setImageURI(uri)
            }
        }
            .removeOnFailureListener{
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
    }
}