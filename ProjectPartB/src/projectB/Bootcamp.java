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
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Bootcamp {

    //constructor
    public Bootcamp(){
    }

    public void insertStudent(Student student, Connection conn){
        PreparedStatement stm = null;
        String query = "INSERT INTO BOOTCAMP.student(first_name, last_name, date_of_birth, tuition_fees) values (?, ?, ?, ?)";
        try {
            stm=conn.prepareStatement(query);
            stm.setString(1, student.getFirstName());
            stm.setString(2, student.getLastName());
            stm.setDate(3, Date.valueOf(student.getDateOfBirth()), java.util.Calendar.getInstance());
            stm.setDouble(4, student.getFees());
            
            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    public void insertTrainer(Trainer trainer, Connection conn){
        PreparedStatement stm = null;
        String query = "INSERT INTO BOOTCAMP.trainer(first_name, last_name, subject) values (?, ?, ?)";
        try {
            stm=conn.prepareStatement(query);
            stm.setString(1, trainer.getFirstName());
            stm.setString(2, trainer.getLastName());
            stm.setString(3, trainer.getSubject());
                        
            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
    }

    public void insertCourse(Course course, Connection conn){
        PreparedStatement stm = null;
        String query = "INSERT INTO BOOTCAMP.course(title, stream, type, start_date, end_date) values (?, ?, ?, ?, ?)";
        try {
            stm=conn.prepareStatement(query);
            stm.setString(1, course.getTitle());
            stm.setString(2, course.getStream());
            stm.setString(3, course.getType());
            stm.setDate(4, Date.valueOf(course.getStartDate()));
            stm.setDate(5, Date.valueOf(course.getEndDate()));
                        
            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    public void insertAssignment(Assignment assignment, Connection conn) { 
        PreparedStatement stm = null;
        String query = "INSERT INTO BOOTCAMP.assignment(title, description, sub_date_time, oral_mark, total_mark) values (?, ?, ?, ?, ?)";
        try {
            stm=conn.prepareStatement(query);
            stm.setString(1, assignment.getTitle());
            stm.setString(2, assignment.getDescription());
            stm.setDate(3, Date.valueOf(assignment.getSubDateTime()), java.util.Calendar.getInstance());
            stm.setDouble(4, assignment.getOralMark());
            stm.setDouble(5, assignment.getTotalMark());
                        
            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public void insertSubscribedStudent(SubscribedStudent subscribedStudent, Connection conn){
        PreparedStatement stm = null;
        String query = "INSERT INTO BOOTCAMP.subscribed_student(course_id, student_id) values (?, ?)";
        try {
            stm=conn.prepareStatement(query);
            stm.setInt(1, subscribedStudent.getCourseId());
            stm.setInt(2, subscribedStudent.getStudentId());
                        
            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public void insertAssignedTrainer(AssignedTrainer assignedTrainer, Connection conn){
        PreparedStatement stm = null;
        String query = "INSERT INTO BOOTCAMP.course_trainer(course_id, trainer_id) values (?, ?)";
        try {
            stm=conn.prepareStatement(query);
            stm.setInt(1, assignedTrainer.getCourseId());
            stm.setInt(2, assignedTrainer.getTrainerId());
                        
            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public void insertCourseAssignment(CourseAssignment courseAssignment, Connection conn){
        PreparedStatement stm = null;
        String query = "INSERT INTO BOOTCAMP.course_assignment(assignment_id, course_id) values (?, ?)";
        try {
            stm=conn.prepareStatement(query);
            stm.setInt(1, courseAssignment.getAssignmentId());
            stm.setInt(2, courseAssignment.getCourseId());
                        
            stm.executeUpdate();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public int giveAssignmentId(Assignment assignment, Connection conn){
        Statement stm = null;

        int assignmentId = 0;
        String query = "SELECT id \n" +
                       "FROM BOOTCAMP.assignment\n" +
                       "WHERE assignment.title = '"+ assignment.getTitle() +"' \n" +
                       "AND assignment.description = '"+ assignment.getDescription()+"' \n" +
                       "AND assignment.sub_date_time = '"+ assignment.getSubDateTime()+"';";
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while ( rs.next() ) {
                assignmentId = rs.getInt("id");
            }
            stm.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assignmentId;
    }
    /**
     * This method prints out all students
     */
    public void printStudentList(Connection conn) {
        Statement stm = null;
        String query = "SELECT * FROM BOOTCAMP.student;";
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while ( rs.next() ) {
                int studentId = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date dateOfBirth = rs.getDate("date_of_birth");
                double fees = rs.getDouble("tuition_fees");
                System.out.println(studentId +". "+
                                   "First Name: " +firstName+
                                   ",Last Name: " +lastName+
                                   ",Date of Birth: " +dateOfBirth+
                                   ",Tuition Fees: " + fees);
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
    }

    /**
     * This method prints out all trainers
     */
    public void printTrainerList(Connection conn) {
        Statement stm = null;
        String query = "SELECT * FROM BOOTCAMP.trainer;";
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while ( rs.next() ) {
                int trainerId = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String subject = rs.getString("subject");
                
                System.out.println(trainerId +". "+
                                   "First Name: " +firstName+
                                   ",Last Name: " +lastName+
                                   ",Subject: " +subject);
                                   
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    /**
     * This method prints out all courses
     */
    public void printCourseList(Connection conn) {
        
        Statement stm = null;
        String query = "SELECT * FROM BOOTCAMP.course;";
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while ( rs.next() ) {
                int courseId = rs.getInt("id");
                String title= rs.getString("title");
                String stream = rs.getString("stream");
                String type = rs.getString("type");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");
                            
                
                System.out.println(courseId +". "+
                                   "Title: " +title+
                                   ",Stream: " +stream+
                                   ",Type: " +type+
                                   ",Start date: " +startDate+
                                   ",End date: " +endDate);
                                   
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method prints out all assignments
     */
    public void printAssignmentList(Connection conn) {
        Statement stm = null;
        String query = "SELECT * FROM BOOTCAMP.assignment;";
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while ( rs.next() ) {
                int assignmentId = rs.getInt("id");
                String title= rs.getString("title");
                String description = rs.getString("description");
                Date subDateTime = rs.getDate("sub_date_time");
                Double oralMark = rs.getDouble("oral_mark");
                Double totalMark = rs.getDouble("total_mark");
                            
                
                System.out.println(assignmentId +". "+
                                   "Title: " +title+
                                   ",Description: " +description+
                                   ",Submission Date: " +subDateTime+
                                   ",Oral Mark: " +oralMark+
                                   ",Total Mark: " +totalMark);
                                   
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method prints out all the students per course
     */
    public void printSubscribedStudentList(Connection conn) {
        
        Statement stm = null;
        String query = "SELECT course.title AS course_title, student.first_name, student.last_name\n" +
                       "FROM course, student, subscribed_student\n" +
                       "WHERE course.id = subscribed_student.course_id \n" +
                       "AND student.id = subscribed_student.student_id\n" +
                       "ORDER BY course.title;";
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while ( rs.next() ) {
                String courseTitle= rs.getString("course_title");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                            
                
                System.out.println("Course : " +courseTitle+
                                   ",Student: " +firstName+
                                   " " +lastName);
                                   
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
    }

    /**
     * This method prints out all the trainers per course
     */
    public void printAssignedTrainerList(Connection conn) {
        Statement stm = null;
        String query = "SELECT course.title AS course_title, trainer.first_name, trainer.last_name \n" +
                       "FROM course, trainer, course_trainer\n" +
                       "WHERE course.id = course_trainer.course_id \n" +
                       "AND trainer.id = course_trainer.trainer_id\n" +
                       "ORDER BY course.title;";
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while ( rs.next() ) {
                String courseTitle= rs.getString("course_title");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                            
                
                System.out.println("Course : " +courseTitle+
                                   ",Trainer: " +firstName+
                                   " " +lastName);
                                   
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * This method prints out all the assignments per course
     */
    public void printCourseAssignments(Connection conn) {
        Statement stm = null;
        String query = "SELECT course.title AS course_title, assignment.title AS assignment_title \n" +
                       "FROM course, assignment, course_assignment\n" +
                       "WHERE course.id = course_assignment.course_id \n" +
                       "AND assignment.id = course_assignment.assignment_id\n" +
                       "ORDER BY course.title;";
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while ( rs.next() ) {
                String courseTitle= rs.getString("course_title");
                String assignmentTitle = rs.getString("assignment_title");
                
                System.out.println("Course : " +courseTitle+
                                   ",Assignment : " +assignmentTitle);
                                   
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * This method prints out all the assignments per course per student
     */
    public void printStudentAssignments(Connection conn) {
        Statement stm = null;
        String query = "SELECT course.title AS course_title, student.first_name, student.last_name, assignment.title AS assignment_title\n" +
                       "FROM course, assignment, student, subscribed_student, course_assignment\n" +
                       "WHERE student.id = subscribed_student.student_id\n" +
                       "AND course.id = subscribed_student.course_id \n" +
                       "AND course.id = course_assignment.course_id\n" +
                       "AND assignment.id = course_assignment.assignment_id\n" +
                       "ORDER BY course.title, student.first_name, student.last_name;";
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while ( rs.next() ) {
                String courseTitle= rs.getString("course_title");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String assignmentTitle = rs.getString("assignment_title");
                
                System.out.println("Course : " +courseTitle+
                                   ", Student : " +firstName+ " " +lastName+
                                   ", Assignment : " + assignmentTitle);
                                   
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    /**
     * This method prints out the students that
     * belong to more than one courses
     */
    public void printStudentInMultipleCourses(Connection conn) {
       Statement stm = null;
        String query = "SELECT student.first_name, student.last_name\n" +
                       "FROM student, subscribed_student\n" +
                       "WHERE student.id = subscribed_student.student_id\n" +
                       "GROUP BY subscribed_student.student_id\n" +
                       "HAVING COUNT(subscribed_student.student_id)>1;";
        try {
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while ( rs.next() ) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                
                System.out.println("Student : " +firstName+ " " +lastName);
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Bootcamp.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}

