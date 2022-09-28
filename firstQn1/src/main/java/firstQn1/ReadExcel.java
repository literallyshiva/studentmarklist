package firstQn1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel {
	private static final Logger logger = LogManager.getLogger(ReadExcel.class);

	public static void main(String[] ar) throws IOException {
		BasicConfigurator.configure();

		File f = new File("student.xlsx");
		try (FileInputStream fis = new FileInputStream(f); XSSFWorkbook excelWorkbook = new XSSFWorkbook(fis);) {

			XSSFSheet excelSheet = excelWorkbook.getSheetAt(0);
			int rows = excelSheet.getPhysicalNumberOfRows();

			List<Student> studentlist = new ArrayList<>();
			XSSFCell cell1;
			XSSFCell cell2;
			XSSFCell cell3;
			XSSFCell cell4;
			XSSFCell cell5;

			for (int i = 0; i < rows; i++) {

				cell1 = excelSheet.getRow(i).getCell(0);
				cell2 = excelSheet.getRow(i).getCell(1);
				cell3 = excelSheet.getRow(i).getCell(2);
				cell4 = excelSheet.getRow(i).getCell(3);
				cell5 = excelSheet.getRow(i).getCell(4);
				Student s = new Student();

				s.setId((int) cell1.getNumericCellValue());
				s.setName(cell2.getStringCellValue());
				s.setPhysicsMark((int) cell3.getNumericCellValue());
				s.setPhysicsGrade(getGrade(s.getPhysicsMark()));
				s.setPhysicsPoint(getgradepoint(s.getPhysicsGrade()));
				s.setMathsMark((int) cell4.getNumericCellValue());
				s.setMathsGrade(getGrade(s.getMathsMark()));
				s.setMathsPoint(getgradepoint(s.getMathsGrade()));
				s.setChemistryMark((int) cell5.getNumericCellValue());
				s.setChemistryGrade(getGrade(s.getChemistryMark()));
				s.setChemistrypoint(getgradepoint(s.getChemistryGrade()));
				s.setTotalMark(sum(s.getPhysicsMark(), s.getMathsMark(), s.getChemistryMark()));
				s.setPercentage(percentages(s.getPhysicsMark(), s.getMathsMark(), s.getChemistryMark()));
				studentlist.add(s);
			}

			logger.info("Enter the admission number of the student");
			Scanner w = new Scanner(System.in);
			int k = w.nextInt();
			w.close();

			for (int i = 0; i < rows; i++) {
				if (k == studentlist.get(i).getId()) {
					Student currentStudent = studentlist.get(i);
					logger.info("Name :" + currentStudent.getName());
					logger.info("Admission Number :" + currentStudent.getId());
					logger.info("Percentage :" + currentStudent.getPercentage());
					logger.info("Physics:" + "\n\t" + "Mark:" + currentStudent.getPhysicsMark()+ "\n\t" + "Grade:"
							+ currentStudent.getPhysicsGrade() + "\n\t" + "Grade point:" + currentStudent.getPhysicsPoint());
					logger.info("Maths:" + "\n\t" + "Mark:" + currentStudent.getMathsMark() + "\n\t" + "Grade:"
							+ currentStudent.getMathsGrade() + "\n\t" + "Grade point:"
							+ currentStudent.getMathsPoint());
					logger.info("Chemistry:" + "\n\t" + "Mark:" + currentStudent.getChemistryMark() + "\n\t"
							+ "Grade:" + currentStudent.getChemistryGrade() + "\n\t" + "Grade point:"
							+ currentStudent.getChemistrypoint());

				}

			}
		} catch (Exception e) {
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

	public static double getgradepoint(String y) {
		double r = 0;

		switch (y) {
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
