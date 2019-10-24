/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectB;

/**
 *
 * @author korti
 */

//import java.util.Scanner;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static String choiceMain, choiceYN;
    public static void main(String[] args) {
        
        Connection conn = null;
        String dbConnection = "jdbc:mysql://localhost/bootcamp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";        
        try {
            conn=DriverManager.getConnection(dbConnection,"root", "ko13rti5S*@");

            Scanner scanner = new Scanner(System.in);
            Bootcamp myBootcamp = new Bootcamp();

            // use US locale to be able to identify floats in the string
            scanner.useLocale(Locale.US);

            do {

                System.out.println("Main Menu");
                System.out.println("1.Insert student");
                System.out.println("2.Insert trainer");
                System.out.println("3.Insert course");
                System.out.println("4.Insert assignment for a specific course");
                System.out.println("5.Subscribe student to a course");
                System.out.println("6.Assign course to trainer");
                System.out.println("7.Print all students");
                System.out.println("8.Print all trainers");
                System.out.println("9.Print all courses");
                System.out.println("10.Print all assignments");
                System.out.println("11.Print all students per course");
                System.out.println("12.Print all trainers per course");
                System.out.println("13.Print all assignments per course");
                System.out.println("14.Print all assignments per course per student");
                System.out.println("15.Print students in multiple courses");
                System.out.println("16.Exit");

                do {
                    System.out.printf("Please give your selection(1-16):");
                    choiceMain = scanner.nextLine();
                } while (  !choiceMain.equals("1") && !choiceMain.equals("2") && !choiceMain.equals("3") && !choiceMain.equals("4") && !choiceMain.equals("5") && !choiceMain.equals("6")
                        && !choiceMain.equals("7") && !choiceMain.equals("8") && !choiceMain.equals("9") && !choiceMain.equals("10") && !choiceMain.equals("11") && !choiceMain.equals("12")
                        && !choiceMain.equals("13")&& !choiceMain.equals("14")&& !choiceMain.equals("15")&& !choiceMain.equals("16"));


                switch (choiceMain) {

                    //Insert student
                    case "1":
                        do {
                            System.out.printf("Give student's first name:");
                            String studentFirstName = scanner.nextLine();
                            if (studentFirstName == null) {
                                System.out.println("!!!!ERROR!!!The first name is null");
                                continue;         // return to initial menu
                            }

                            System.out.printf("Give student's last name:");
                            String studentLastName = scanner.nextLine();
                            if (studentLastName == null) {
                                System.out.println("!!!!ERROR!!!The last name is null");
                                continue;         // return to initial menu
                            }

                            System.out.printf("Give student's date of birth in the following format (YYYY-MM-DD):");
                            String date = scanner.nextLine();
                            if (date == null) {
                                System.out.println("!!!!ERROR!!!The date of birth is null");
                                continue;         // return to initial menu
                            }

                            System.out.printf("Give student's tuition fees:");
                            double fees = scanner.nextDouble();
                            scanner.nextLine();

                            myBootcamp.insertStudent(new Student(studentFirstName, studentLastName, date, fees), conn);
                            System.out.println("\nStudent inserted successfully.");
                            do{
                                System.out.printf("Do you want to add more students? (y,n): ");
                                choiceYN= scanner.nextLine();
                            }while (!choiceYN.equalsIgnoreCase("y") && !choiceYN.equalsIgnoreCase("n"));

                        }while(choiceYN.equalsIgnoreCase("y"));
                        break;

                    //Insert trainer
                    case "2":
                        do {
                            System.out.printf("Give trainers's first name:");
                            String trainerFirstName = scanner.nextLine();
                            if (trainerFirstName == null) {
                                System.out.println("!!!!ERROR!!!The first name is null");
                                continue;         // return to initial menu
                            }

                            System.out.printf("Give trainers's last name:");
                            String trainerLastName = scanner.nextLine();
                            if (trainerLastName == null) {
                                System.out.println("!!!!ERROR!!!The last name is null");
                                continue;         // return to initial menu
                            }

                            System.out.printf("Give trainer's subject:");
                            String subject = scanner.nextLine();
                            if (subject == null) {
                                System.out.println("!!!!ERROR!!!The subject is null");
                                continue;         // return to initial menu
                            }

                            myBootcamp.insertTrainer(new Trainer(trainerFirstName, trainerLastName, subject), conn);
                            System.out.println("\nTrainer inserted successfully.");

                            do{
                                System.out.printf("Do you want to add more trainers? (y,n): ");
                                choiceYN= scanner.nextLine();
                            }while (!choiceYN.equalsIgnoreCase("y") && !choiceYN.equalsIgnoreCase("n"));

                        }while(choiceYN.equalsIgnoreCase("y"));
                        break;

                    //Insert course
                    case "3":
                        do {
                            System.out.printf("Give course title:");
                            String courseTitle = scanner.nextLine();
                            if (courseTitle == null) {
                                System.out.println("!!!!ERROR!!!The title is null");
                                continue;         // return to initial menu
                            }

                            System.out.printf("Give course stream:");
                            String courseStream = scanner.nextLine();
                            if (courseStream == null) {
                                System.out.println("!!!!ERROR!!!The stream is null");
                                continue;         // return to initial menu
                            }

                            System.out.printf("Give course type:");
                            String courseType = scanner.nextLine();
                            if (courseType == null) {
                                System.out.println("!!!!ERROR!!!The type is null");
                                continue;         // return to initial menu
                            }

                            System.out.printf("Give course start date in the following format (YYYY-MM-DD):");
                            String startDate = scanner.nextLine();
                            if (startDate == null) {
                                System.out.println("!!!!ERROR!!!The start date is null");
                                continue;         // return to initial menu
                            }

                            System.out.printf("Give course end date in the following format (YYYY-MM-DD):");
                            String endDate = scanner.nextLine();
                            if (endDate == null) {
                                System.out.println("!!!!ERROR!!!The end date is null");
                                continue;         // return to initial menu
                            }

                            myBootcamp.insertCourse(new Course(courseTitle, courseStream, courseType, startDate, endDate), conn);
                            System.out.println("\nCourse inserted successfully.");

                            do{
                                System.out.printf("Do you want to add more courses? (y,n): ");
                                choiceYN= scanner.nextLine();
                            }while (!choiceYN.equalsIgnoreCase("y") && !choiceYN.equalsIgnoreCase("n"));

                        }while(choiceYN.equalsIgnoreCase("y"));
                        break;

                    // Insert assignment for a specific course
                    case "4":
                        do {
                            System.out.printf("Give title:");
                            String assignmentTitle = scanner.nextLine();
                            if (assignmentTitle == null) {
                                System.out.println("!!!!ERROR!!!The title is null");
                                continue;         // return to initial menu
                            }

                            System.out.printf("Give description:");
                            String description = scanner.nextLine();
                            if (description == null) {
                                System.out.println("!!!!ERROR!!!The description is null");
                                continue;         // return to initial menu
                            }

                            System.out.printf("Give subDateTime in the following format (YYYY-MM-DD):");
                            String dateTime = scanner.nextLine();
                            if (dateTime == null) {
                                System.out.println("!!!!ERROR!!!The date is null");
                                continue;         // return to initial menu
                            }

                            System.out.printf("Give oralMark:");
                            double oralMark = scanner.nextDouble();

                            System.out.printf("Give totalMark:");
                            double totalMark = scanner.nextDouble();
                            scanner.nextLine();


                            System.out.println("Now select one of the available courses to relate your assignment");
                            myBootcamp.printCourseList(conn);
                            System.out.printf("Please give your selection:");
                            String selectedCourse = scanner.nextLine();

                            Assignment assignment = new Assignment(assignmentTitle, description, dateTime, oralMark, totalMark);
                            myBootcamp.insertAssignment(assignment, conn);
                            System.out.println("\nAssignment inserted successfully.");

                            int assignmentId = myBootcamp.giveAssignmentId(assignment, conn);
                            myBootcamp.insertCourseAssignment(new CourseAssignment(assignmentId, Integer.parseInt(selectedCourse)), conn);

                            do{
                                System.out.printf("Do you want to add more assignments? (y,n): ");
                                choiceYN= scanner.nextLine();
                            }while (!choiceYN.equalsIgnoreCase("y") && !choiceYN.equalsIgnoreCase("n"));
                        }while(choiceYN.equals("y"));


                        break;

                    // Subscribe student to a course
                    case "5":
                        System.out.println("Select one of the following students");
                        myBootcamp.printStudentList(conn);
                        System.out.printf("Please give your selection:");
                        String studentChoice = scanner.nextLine();

                        System.out.println("Subscribe the selected student to one of the following courses");
                        myBootcamp.printCourseList(conn);
                        System.out.printf("Please give your selection:");
                        String courseChoiceForStudent = scanner.nextLine();

                        myBootcamp.insertSubscribedStudent(new SubscribedStudent(Integer.parseInt(studentChoice), Integer.parseInt(courseChoiceForStudent)), conn);
                        System.out.println("\nStudent subscribed successfully to the course.");
                        break;

                    // Assign course to trainer
                    case "6":
                        System.out.println("Select one of the following trainers");
                        myBootcamp.printTrainerList(conn);
                        System.out.printf("Please give your selection:");
                        String trainerChoice = scanner.nextLine();

                        System.out.println("Assign a course to the selected trainer");
                        myBootcamp.printCourseList(conn);
                        System.out.printf("Please give your selection:");
                        String courseChoiceForTrainer = scanner.nextLine();

                        myBootcamp.insertAssignedTrainer(new AssignedTrainer(Integer.parseInt(trainerChoice), Integer.parseInt(courseChoiceForTrainer)), conn);
                        System.out.println("\nCourse assigned successfully to the trainer.");
                        break;

                    // Print all Students
                    case "7":
                        myBootcamp.printStudentList(conn);
                        break;

                    // Print all Trainers
                    case "8":
                        myBootcamp.printTrainerList(conn);
                        break;

                    // Print all Courses
                    case "9":
                        myBootcamp.printCourseList(conn);
                        break;
                    // Print all Assignments
                    case "10":
                        myBootcamp.printAssignmentList(conn);
                        break;
                    // print all Students per Course
                    case "11":
                        myBootcamp.printSubscribedStudentList(conn);
                        break;
                    // Print all Trainers per Course
                    case "12":
                        myBootcamp.printAssignedTrainerList(conn);
                        break;
                    // Print all Assignments per Course
                    case "13":
                        myBootcamp.printCourseAssignments(conn);
                        break;
                    // Print all Assignments per Course per Student
                    case "14":
                        myBootcamp.printStudentAssignments(conn);
                        break;
                    // Print a list of Students that belong to more than one courses
                    case "15":
                        myBootcamp.printStudentInMultipleCourses(conn);
                        break;
                    // Exit the main menu
                    case "16":
                        System.out.println("Exit!");
                        break;
                }
                System.out.println("------------------\n");

            } while (!choiceMain.equals("16"));

            scanner.close();
            conn.close();
        
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

