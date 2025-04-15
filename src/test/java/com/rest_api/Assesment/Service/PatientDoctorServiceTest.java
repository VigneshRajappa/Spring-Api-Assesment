package com.rest_api.Assesment.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.rest_api.Assesment.Exception.InvalidIDException;
import com.rest_api.Assesment.Model.Doctor;
import com.rest_api.Assesment.Model.Patient;
import com.rest_api.Assesment.Model.PatientDoctor;
import com.rest_api.Assesment.Repository.PatientDoctorRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PatientDoctorServiceTest {

	@InjectMocks
    private PatientDoctorService patientDoctorService;

    @Mock
    private PatientDoctorRepository patientDoctorRepository;

    private Doctor doctor;
    private Patient patient1;
    private Patient patient2;
    private PatientDoctor pd1;
    private PatientDoctor pd2;

    @BeforeEach
    public void init() {
        doctor = new Doctor();
        doctor.setId(1);
        doctor.setName("Dr. John");

        patient1 = new Patient();
        patient1.setId(101);
        patient1.setName("Patient A");

        patient2 = new Patient();
        patient2.setId(102);
        patient2.setName("Patient B");

        pd1 = new PatientDoctor();
        pd1.setId(1);
        pd1.setDoctor(doctor);
        pd1.setPatient(patient1);

        pd2 = new PatientDoctor();
        pd2.setId(2);
        pd2.setDoctor(doctor);
        pd2.setPatient(patient2);
    }

    @Test
    public void testGetByDoctorId_ValidDoctorId_ReturnsList() throws InvalidIDException {
        int docId = 1;
        List<PatientDoctor> expectedList = Arrays.asList(pd1, pd2);

        when(patientDoctorRepository.getPatientDoctorById(docId)).thenReturn(expectedList);

        List<PatientDoctor> actualList = patientDoctorService.getByDoctorId(docId);

        assertEquals(2, actualList.size());
        assertEquals(expectedList, actualList);
        verify(patientDoctorRepository, times(1)).getPatientDoctorById(docId);
    }

    @Test
    public void testGetByDoctorId_InvalidDoctorId_ThrowsException() {
        int docId = 99;

        when(patientDoctorRepository.getPatientDoctorById(docId)).thenReturn(Collections.emptyList());

        InvalidIDException exception = assertThrows(InvalidIDException.class, () -> {
            patientDoctorService.getByDoctorId(docId);
        });

        assertEquals("Given Doctor id is Invalid....", exception.getMessage());
        verify(patientDoctorRepository, times(1)).getPatientDoctorById(docId);
    }
}
