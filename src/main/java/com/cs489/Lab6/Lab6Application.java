package com.cs489.Lab6;

import com.cs489.Lab6.model.*;
import com.cs489.Lab6.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Lab6Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab6Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			AddressRepository addressRepository,
			SurgeryRepository surgeryRepository,
			DentistRepository dentistRepository,
			PatientRepository patientRepository,
			AppointmentRepository appointmentRepository) {
		return args -> {
			Address dummyAddress = Address.builder()
					.street("123 Fake Street")
					.city("Any City")
					.postalCode("12345")
					.build();
			var address = addressRepository.save(dummyAddress);

			// Creating Surgeries

			var s10 = surgeryRepository.save(Surgery.builder()
					.surgeryId("S10")
					.name("Surgery 10")
					.phone("123-456-7890")
					.surgeryAddress(address)
					.build());

			var s13 = surgeryRepository.save(Surgery.builder()
					.surgeryId("S13")
					.name("Surgery 13")
					.phone("555-555-5555")
					.surgeryAddress(address)
					.build());

			var s15 = surgeryRepository.save(Surgery.builder()
					.surgeryId("S15")
					.name("Surgery 15")
					.phone("098-765-4321")
					.surgeryAddress(address)
					.build());

			// Creating Dentists

			var d1 = dentistRepository.save(Dentist.builder()
					.firstName("Tony")
					.lastName("Smith")
					.phone("111-222-3333")
					.email("tony.smith@email.com")
					.specialization("Orthodontics")
					.build());

			var d2 = dentistRepository.save(Dentist.builder()
					.firstName("Helen")
					.lastName("Pearson")
					.phone("222-333-4444")
					.email("helen.pearson@email.com")
					.specialization("Endodontics")
					.build());

			var d3 = dentistRepository.save(Dentist.builder()
					.firstName("Robin")
					.lastName("Plevin")
					.phone("333-444-5555")
					.email("robin.plevin@email.com")
					.specialization("Pedodontics")
					.build());

			// Creating Patients

			var p100 = patientRepository.save(Patient.builder()
							.patientId("P100")
							.firstName("Gillian")
							.lastName("White")
							.phone("444-555-6666")
							.email("gillian.white@email.com")
							.dateOfBirth(LocalDate.of(1970, 1, 1))
							.patientAddress(address)
					.build());

			var p105 = patientRepository.save(Patient.builder()
					.patientId("P105")
					.firstName("Jill")
					.lastName("Bell")
					.phone("555-666-7777")
					.email("jill.bell@email.com")
					.dateOfBirth(LocalDate.of(1980, 2, 2))
					.patientAddress(address)
					.build());

			var p108 = patientRepository.save(Patient.builder()
					.patientId("P108")
					.firstName("Ian")
					.lastName("MacKay")
					.phone("666-777-8888")
					.email("ian.mackay@email.com")
					.dateOfBirth(LocalDate.of(1990, 3, 3))
					.patientAddress(address)
					.build());

			var p110 = patientRepository.save(Patient.builder()
					.patientId("P110")
					.firstName("John")
					.lastName("Walker")
					.phone("777-888-9999")
					.email("john.walker@email.com")
					.dateOfBirth(LocalDate.of(2000, 4, 4))
					.patientAddress(address)
					.build());

			// Creating Appointments

			appointmentRepository.save(Appointment.builder()
					.patient(p100)
					.surgery(s15)
					.appointmentDateTime(LocalDateTime.of(2013, 9, 12, 10, 0))
					.dentist(d1)
					.status("Scheduled")
					.build());

			appointmentRepository.save(Appointment.builder()
					.patient(p105)
					.surgery(s15)
					.appointmentDateTime(LocalDateTime.of(2013, 9, 12, 12, 0))
					.dentist(d1)
					.status("Requested")
					.build());

			appointmentRepository.save(Appointment.builder()
					.patient(p108)
					.surgery(s10)
					.appointmentDateTime(LocalDateTime.of(2013, 9, 13, 10, 0))
					.dentist(d2)
					.status("Scheduled")
					.build());

			appointmentRepository.save(Appointment.builder()
					.patient(p108)
					.surgery(s10)
					.appointmentDateTime(LocalDateTime.of(2013, 9, 14, 14, 0))
					.dentist(d2)
					.status("Requested")
					.build());

			appointmentRepository.save(Appointment.builder()
					.patient(p105)
					.surgery(s15)
					.appointmentDateTime(LocalDateTime.of(2013, 9, 14, 16, 30))
					.dentist(d3)
					.status("Scheduled")
					.build());

			appointmentRepository.save(Appointment.builder()
					.patient(p110)
					.surgery(s13)
					.appointmentDateTime(LocalDateTime.of(2013, 9, 15, 18, 0))
					.dentist(d3)
					.status("Scheduled")
					.build());
			// Display Appointments
			List<Appointment> appointments = appointmentRepository.findAll();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

			System.out.println("dentistName \t\t\tpatNo \t\t\tpatName \t\t\tappointment date time \t\tsurgeryNo");
			for (Appointment appointment: appointments) {
				System.out.printf("%-20s \t%-10s \t%-20s \t%-25s \t%-10s \n",
						appointment.getDentist().getFirstName() + " " + appointment.getDentist().getLastName(),
						appointment.getPatient().getPatientId(),
						appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName(),
						appointment.getAppointmentDateTime().format(formatter),
						appointment.getSurgery().getSurgeryId());
			}
		};



	}
}
