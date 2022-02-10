package ui;

import model.Doctor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UIPaitentMenu {
    public static void showPaitentMenu(){
        int response = 0;
        do {
            System.out.println("\n\n");
            System.out.println("Patient");
            System.out.println("Welcome");
            System.out.println("Welcome: "+ UIMenu.patientLogged.getName());
            System.out.println("1. Book an appointment");
            System.out.println("2. My Apointment");
            System.out.println("0. Logout");

            Scanner sc = new Scanner(System.in);
            response = Integer.valueOf(sc.nextLine());

            switch (response){
                case 1:
                    showBookAppointmentMenu();
                    break;
                case 2:
                    showPatientAppoinments();
                    break;
                case 0:
                    UIMenu.showMenu();
                    break;
            }


        }while (response !=0);
    }

    private static void showBookAppointmentMenu(){
        int response = 0;
        do {
            System.out.println(":: Book an appointemnt");
            System.out.println(":: Selecciona una fecha");
            //Numeracion de lista de fechas
            //Indice decha seleccionada
            //[doctors]
            //1.- doctor 1
                //-1 fecha1
                //-2 fecha2
            //2.- doctor 2
            //3.- doctor 3
            Map<Integer, Map<Integer, Doctor>> doctors = new TreeMap<>();
            int k=0;
            for (int i = 0; i < UIDoctorMenu.doctorsAvailablesApointments.size(); i++) {
                ArrayList<Doctor.AvailableAppointment> availableAppointments = UIDoctorMenu.doctorsAvailablesApointments.get(i).getAvailableAppointments();

                Map<Integer, Doctor> doctoAppoinmet = new TreeMap<>();
                for (int j = 0; j < availableAppointments.size(); j++) {
                    k++;
                    System.out.println(k + ". " + availableAppointments.get(j).getDate());
                    doctoAppoinmet.put(Integer.valueOf(j),UIDoctorMenu.doctorsAvailablesApointments.get(i));
                    doctors.put(Integer.valueOf(k), doctoAppoinmet);

                }
            }
            Scanner sc = new Scanner(System.in);
            int responseDateSelected = Integer.valueOf(sc.nextLine());
            Map<Integer, Doctor> doctorAvailableSelected = doctors.get(responseDateSelected);
            Integer indexDate =0;
            Doctor doctorSelected = new Doctor("","");
            for (Map.Entry<Integer, Doctor> doc:doctorAvailableSelected.entrySet()) {
                indexDate = doc.getKey();
                doctorSelected = doc.getValue();

            }

            System.out.println(doctorSelected.getName() +
                    ". Date "+ doctorSelected.getAvailableAppointments().get(indexDate).getDate() +
                    ". Time : " +
                    doctorSelected.getAvailableAppointments().get(indexDate).getTime());

            System.out.println("Confirm you appointment: \n1.Yes \n2.Change Data" );
            response = Integer.valueOf(sc.nextLine());

            if (response == 1){
                UIMenu.patientLogged.addAppointmentDoctors(doctorSelected,doctorSelected.getAvailableAppointments().get(indexDate).getDate(null),
                        doctorSelected.getAvailableAppointments().get(indexDate).getTime());

                showPaitentMenu();


            }

        }while (response !=0);
    }

    private static void showPatientAppoinments(){
        int response =0;
        do {
            System.out.println("::My Appointments");
            if (UIMenu.patientLogged.getAppointmentDoctors().size() ==0){
                System.out.println("Dont have appointmen");
                break;
            }
            for (int i = 0; i < UIMenu.patientLogged.getAppointmentDoctors().size(); i++) {
                int j=i+1;
                System.out.println(j + ". " +
                       "Date: " + UIMenu.patientLogged.getAppointmentDoctors().get(i).getDate() +
                        "Time: " + UIMenu.patientLogged.getAppointmentDoctors().get(i).getTime() +
                        "\n Doctor " + UIMenu.patientLogged.getAppointmentDoctors().get(i).getDoctor().getName());
                
            }

            System.out.println("0. Return");

        }while(response !=0);
    }
}
