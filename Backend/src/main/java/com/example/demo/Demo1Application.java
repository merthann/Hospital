package com.example.demo;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.repositories.DoctorRepository;
/*mvc jpa api hibernate
GET, veri listelemek ve görüntülemek için kullanılır.
POST, veri eklemek için kullanılan bir istektir.
DELETE, veri silmek için kullanılır.
PATCH, verinin bir kısmının güncellenmesi için kullanılan bir istektir.
 */
import com.example.demo.repositories.PatientRepository;
import com.example.demo.repositories.PoliclinicRepository;
import com.example.demo.service.AsistanService;
import com.example.demo.service.DoctorService;
import com.example.demo.service.PatientService;



@SpringBootApplication
@EnableWebMvc
@EnableTransactionManagement
public class Demo1Application{
	
	
	@Autowired
    private PoliclinicRepository PoliclinicRepository;

    @Autowired
    private DoctorRepository DoctorRepository;
    
    @Autowired
    private PatientRepository Patientrepository;
    
    @Autowired
    private PatientService patientservice;
    
    @Autowired 
    private DoctorService doctorservice;
    
    
    @Autowired AsistanService asistanservice;

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
		
		}
	/*
	@Override
    public void run(String... args) throws Exception {
        // Create an Asistan
        Asistan asistan = asistanservice.createAsistan("John", "Doe", "Mon-Fri");
        System.out.println("Created Asistan: " + asistan);

        // Create a Doctor
        Doctor doctor = doctorservice.createDoctor("Dr.", "Jane", "Smith", 1L, asistan.getAsistanid());
        System.out.println("Created Doctor: " + doctor);
    }
	*/
	
	
	
	/*
	@Bean
    public CommandLineRunner demo() {
	return (args) ->{
		
		Asistan asistan = new Asistan();
		asistan.setFname("mahmut");
		asistan.setLname("kadir");
		asistan.setWorkdays("pzt-çrş");
		
		
		Policlinic polik = new Policlinic();
		polik.setName("Kulak burun boğaz");
		polik.setFloor(1);
		polik = PoliclinicRepository.save(polik);
		
		Doctor Doctor = new Doctor();
		Doctor.setFname("Sadi");
		Doctor.setLname("Kaput");
		Doctor.setTitle("Prof. Dr.");
		Doctor.setAsistan(asistan.getAsistanid());
		Doctor.setPoliclinic(polik);
		DoctorRepository.save(Doctor);
		
		
		Patient hasta = new Patient();
		hasta.setFname("Ahmet");
		hasta.setIdentityno(234543623);
		hasta.setLname("Keriz");
		Patientrepository.save(hasta);
	
	};
}*/

	
}