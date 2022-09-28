package firstQn1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ExceltoDb {
	private static final Logger logger = LogManager.getLogger(ExceltoDb.class);
	public static void main(String[] ar) throws IOException, SQLException {
		BasicConfigurator.configure();
		File f = new File("student.xlsx");
		try (FileInputStream fis = new FileInputStream(f); XSSFWorkbook excelWorkbook = new XSSFWorkbook(fis);) {

			XSSFSheet Sheet = excelWorkbook.getSheetAt(0);
			int rows = Sheet.getPhysicalNumberOfRows();

			List<Student> studentlist = new ArrayList<>();
			XSSFCell coloumn1;
			XSSFCell coloumn2;
			XSSFCell coloumn3;
			XSSFCell coloumn4;
			XSSFCell coloumn5;

			for (int i = 0; i < rows; i++) {

				coloumn1 = Sheet.getRow(i).getCell(0);
				coloumn2 = Sheet.getRow(i).getCell(1);
				coloumn3 = Sheet.getRow(i).getCell(2);
				coloumn4 = Sheet.getRow(i).getCell(3);
				coloumn5 = Sheet.getRow(i).getCell(4);
				Student s = new Student();

				s.setId((int) coloumn1.getNumericCellValue());
				s.setName(coloumn2.getStringCellValue());
				s.setPhysicsMark((int) coloumn3.getNumericCellValue());
				s.setPhysicsGrade(getGrade1(s.getPhysicsMark()));
				s.setPhysicsPoint(getgradepoint(s.getPhysicsGrade()));
				s.setMathsMark((int) coloumn4.getNumericCellValue());
				s.setMathsGrade(getGrade1(s.getMathsMark()));
				s.setMathsPoint(getgradepoint(s.getMathsGrade()));
				s.setChemistryMark((int) coloumn5.getNumericCellValue());
				s.setChemistryGrade(getGrade1(s.getChemistryMark()));
				s.setChemistrypoint(getgradepoint(s.getChemistryGrade()));
				s.setTotalMark(sum(s.getPhysicsMark(), s.getMathsMark(), s.getChemistryMark()));
				s.setPercentage(percentages(s.getPhysicsMark(), s.getMathsMark(), s.getChemistryMark()));
				studentlist.add(s);
			}

			writeJson(studentlist);
			saveToDB(studentlist);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String getGrade1(int chemistryMark) {
		return null;
	}

	public static void saveToDB(List<Student> studentlist) throws SQLException {

		String jdbcURL = "jdbc:mysql://localhost:3306/student?useSSL=false";
		String username = "root";
		String password = "root";
		String insertQuery = "INSERT INTO student (id, name, Phymark, PhyGrade, PhyPoint, Mathsmark, MathsGrade, MathsPoint, Chemmark, ChemGrade, Chempoint,Percentage, TotalMark) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?)";
		String selectQuery = "SELECT * FROM student WHERE Id = ?";
		try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
				PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
				PreparedStatement selectStatement = connection.prepareStatement(selectQuery);) {
			connection.setAutoCommit(false);

			for (int i = 0; i < studentlist.size(); i++) {

				insertStatement.setInt(1, studentlist.get(i).getId());
				insertStatement.setString(2, studentlist.get(i).getName());
				insertStatement.setInt(3, studentlist.get(i).getPhysicsMark());
				insertStatement.setString(4, studentlist.get(i).getPhysicsGrade());
				insertStatement.setDouble(5, studentlist.get(i).getPhysicsPoint());
				insertStatement.setInt(6, studentlist.get(i).getMathsMark());
				insertStatement.setString(7, studentlist.get(i).getMathsGrade());
				insertStatement.setDouble(8, studentlist.get(i).getMathsPoint());
				insertStatement.setInt(9, studentlist.get(i).getChemistryMark());
				insertStatement.setInt(9, studentlist.get(i).getChemistryMark());
				insertStatement.setString(10, studentlist.get(i).getChemistryGrade());
				insertStatement.setDouble(11, studentlist.get(i).getChemistrypoint());
				insertStatement.setInt(12, studentlist.get(i).getTotalMark());
				insertStatement.setInt(13, studentlist.get(i).getPercentage());
				insertStatement.execute();

			}
			connection.commit();

			logger.info("Enter the admission number of the sudent you want to search");
			Scanner w = new Scanner(System.in);
			int k = w.nextInt();
			w.close();
			selectStatement.setInt(1, k);

			ResultSet rs = selectStatement.executeQuery();

			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setPhysicsMark(rs.getInt(3));
				s.setPhysicsGrade(rs.getString(4));
				s.setPhysicsPoint(rs.getDouble(5));
				s.setMathsMark(rs.getInt(6));
				s.setMathsGrade(rs.getString(7));
				s.setMathsPoint(rs.getDouble(8));
				s.setChemistryMark(rs.getInt(9));
				s.setChemistryGrade(rs.getString(10));
				s.setChemistrypoint(rs.getDouble(11));
				s.setPercentage(rs.getInt(12));
				s.setTotalMark(rs.getInt(13));
				ObjectMapper mapper = new ObjectMapper();

				String JSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(s);
				logger.info(JSON);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void writeJson(List<Student> studentlist) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		File file = new File("JSON\\student.json");
		try {
			//  Java object info JSON file.
			mapper.writeValue(file, studentlist);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getGrade(int mark) {
		String grade;
		if (91 <= mark && 100 >= mark) {
			grade = "A1";
			return grade;
		}

		else if (81 <= mark && 90 >= mark) {
			grade = "A2";
			return grade;
		} else if (71 <= mark && 80 >= mark) {
			grade = "B1";
			return grade;
		}

		else if (61 <= mark && 70 >= mark) {
			grade = "B2";
			return grade;
		} else if (51 <= mark && 60 >= mark) {
			grade = "C1";
			return grade;
		} else if (41 <= mark && 50 >= mark) {
			grade = "C2";
			return grade;
		} else if (33 <= mark && 40 >= mark) {
			grade = "D";
			return grade;
		} else if (21 <= mark && 32 >= mark) {
			grade = "E1";
			return grade;
		}

		else {
			grade = "E2";
			return grade;
		}

	}

	public static double getgradepoint(String grade) {
		double r = 0;

		switch (grade) {
		case "A1":
			r = 10.0;
			break;
		case "A2":
			r = 9.0;
			break;
		case "B1":
			r = 8.0;
			break;
		case "B2":
			r = 7.0;
			break;
		case "C1":
			r = 6.0;
			break;
		case "C2":
			r = 5.0;
			break;
		case "D":
			r = 4.0;
			break;

		case "E1":
			r = 3.0;
			break;

		case "E2":
			r = 0.0;
			break;
		default:
			r = 0.0;
			break;

		}
		return r;
	}

	public static int sum(int a, int b, int c) {
		return a + b + c;
	}

	public static int percentages(int d, int e, int f) {
		
		return sum(d, e, f) * 100 / 300;
	}

}
