/* 
 * File:   GradeCalculator.cpp
 * Author: Darwin Castillo
 * Description: A program will be created to display grade averages to the user.
 *              The user will be able to enter their grade information. The
 *              output will reveal the averages of each section to the user.
 */

#include <iostream>
#include <iomanip>
#include <cstring>

using namespace std;

void setStuInfo(char [80], char [80], char [80], char [80], char [80]);
void getClassInfo(int &, char [80]);
void getCategoryInfo(char [80], int &, double &);
void getClassGrades(int &, int &, int &, int &, int &);
void getClassGrade(int &);
void calcUnweightedGradesAvg(double &, int &, int &, int &, int &, int &, int);
void calcUnweightedGradeAvg(double &, int, int);
void calcWeightedGradesAvg(double &, double, double);
void calcWeightedGradeAvg(double &, double, double);
void calcMidtermAvg(double &, double, double, double);
void displayReportHeading(char [80], int);
void displayStuInputHeading(char [80], char [80], char [80]);
void displayGradesData(char [80], int, int, int, int, int, int, double, double);
void displayGradeData(char [80], int, int, double, double);
void displayMidTermAvg(double);

int main(void) 
{
    char   collegeName[80], firstName[80], lastName[80], jNumber[80],
           collegeMajor[80], className[80], categoryOneName[80], 
           categoryTwoName[80], categoryThreeName[80];
    int    classSemester, maxPointsOne, maxPointsTwo, maxPointsThree,
           gradeOnePointsOne, gradeTwoPointsOne, gradeThreePointsOne,
           gradeFourPointsOne, gradeFivePointsOne,
           gradeOnePointsTwo, gradeTwoPointsTwo, gradeThreePointsTwo,
           gradeFourPointsTwo, gradeFivePointsTwo, gradeOnePointsThree;
    double categoryOneWeight, categoryTwoWeight, categoryThreeWeight,
           unweightedGradesAvgOne, unweightedGradesAvgTwo, 
           unweightedGradeAvgOne, weightedGradesAvgOne, weightedGradesAvgTwo,
           weightedGradeAvgOne, midtermAvg;
    
    setStuInfo(collegeName, firstName, lastName, jNumber, collegeMajor);
    
    getClassInfo(classSemester, className);
    
    getCategoryInfo(categoryOneName, maxPointsOne, categoryOneWeight);
    getClassGrades(gradeOnePointsOne, gradeTwoPointsOne, gradeThreePointsOne, 
                   gradeFourPointsOne, gradeFivePointsOne);
    
    getCategoryInfo(categoryTwoName, maxPointsTwo, categoryTwoWeight);
    getClassGrades(gradeOnePointsTwo, gradeTwoPointsTwo, gradeThreePointsTwo, 
                   gradeFourPointsTwo, gradeFivePointsTwo);
   
    getCategoryInfo(categoryThreeName, maxPointsThree, categoryThreeWeight);
    getClassGrade(gradeOnePointsThree);
    
    calcUnweightedGradesAvg(unweightedGradesAvgOne, gradeOnePointsOne, 
                            gradeTwoPointsOne, gradeThreePointsOne, 
                            gradeFourPointsOne, gradeFivePointsOne,
                            maxPointsOne);
    calcUnweightedGradesAvg(unweightedGradesAvgTwo, gradeOnePointsTwo, 
                            gradeTwoPointsTwo, gradeThreePointsTwo, 
                            gradeFourPointsTwo, gradeFivePointsTwo,
                            maxPointsTwo);
    
    calcUnweightedGradeAvg(unweightedGradeAvgOne, gradeOnePointsThree, 
                           maxPointsThree);
    
    calcWeightedGradesAvg(weightedGradesAvgOne, unweightedGradesAvgOne, 
                          categoryOneWeight);
    calcWeightedGradesAvg(weightedGradesAvgTwo, unweightedGradesAvgTwo, 
                          categoryTwoWeight);
    
    calcWeightedGradeAvg(weightedGradeAvgOne, unweightedGradeAvgOne,
                         categoryThreeWeight);
    
    calcMidtermAvg(midtermAvg, weightedGradesAvgOne, weightedGradesAvgTwo, 
                   weightedGradeAvgOne);
    
    displayReportHeading(collegeName, classSemester);
    
    displayStuInputHeading(className, lastName, firstName);
    
    displayGradesData(categoryOneName, gradeOnePointsOne, gradeTwoPointsOne,
                     gradeThreePointsOne, gradeFourPointsOne,
                     gradeFivePointsOne, maxPointsOne, categoryOneWeight,
                     unweightedGradesAvgOne);
    displayGradesData(categoryTwoName, gradeOnePointsTwo, gradeTwoPointsTwo,
                     gradeThreePointsTwo, gradeFourPointsTwo,
                     gradeFivePointsTwo, maxPointsTwo, categoryTwoWeight, 
                     unweightedGradesAvgTwo);
    displayGradeData(categoryThreeName, gradeOnePointsThree, maxPointsThree, 
                      categoryThreeWeight, 
                      unweightedGradeAvgOne);
    
    displayMidTermAvg(midtermAvg);
    
    return 0;
}

void setStuInfo(char collegeName[80], char firstName[80], char lastName[80],
                char jNumber[80], char collegeMajor[80])
{
    strcpy(collegeName, "Jefferson Community College");
    strcpy(firstName, "Darwin");
    strcpy(lastName, "Castillo");
    strcpy(jNumber, "00150376");
    strcpy(collegeMajor, "Computer Science");
}

void getClassInfo(int &classSemester, char className[80])
{
    cout << "Class semester: ";
    cin >> classSemester;
    cout << "Class name: ";
    cin >> className;
    cout << endl;
}

void getCategoryInfo(char categoryName[80], int &maxPoints, 
                     double &categoryWeight)
{
    cout << "Category: ";
    cin >> categoryName;
    cout << "Category max points: ";
    cin >> maxPoints;
    cout << "Category weight: ";
    cin >> categoryWeight; 
}

void getClassGrades(int &gradeOnePoints, int &gradeTwoPoints, 
                    int &gradeThreePoints, int &gradeFourPoints, 
                    int &gradeFivePoints)
{
    cout << "Grade: ";
    cin >> gradeOnePoints;
    cout << "Grade: ";
    cin >> gradeTwoPoints;
    cout << "Grade: ";
    cin >> gradeThreePoints;
    cout << "Grade: ";
    cin >> gradeFourPoints;
    cout << "Grade: ";
    cin >> gradeFivePoints;
    cout << endl;
}

void getClassGrade(int &gradeOnePoints)
{
    cout << "Grade: ";
    cin >> gradeOnePoints;
    cout << endl << endl;
}

void calcUnweightedGradesAvg(double &unweightedGradesAvg, int &gradeOnePoints, 
                             int &gradeTwoPoints, int &gradeThreePoints, 
                             int &gradeFourPoints, int &gradeFivePoints, 
                             int maxPoints)
{
  unweightedGradesAvg = (gradeOnePoints + gradeTwoPoints + gradeThreePoints + 
                        gradeFourPoints + gradeFivePoints) / (double)maxPoints
                        * 100;
}

void calcUnweightedGradeAvg(double &unweightedGradeAvg, int gradeOnePoints, 
                            int maxPoints)
{
    unweightedGradeAvg = gradeOnePoints / (double)maxPoints * 100;
}

void calcWeightedGradesAvg(double &weightedGradesAvg, 
                           double unweightedGradesAvg, double categoryWeight)
{
    weightedGradesAvg = unweightedGradesAvg * categoryWeight;
}

void calcWeightedGradeAvg(double &weightedGradeAvg, double unweightedGradeAvg,
                          double categoryWeight)
{
    weightedGradeAvg = unweightedGradeAvg * categoryWeight;
}

void calcMidtermAvg(double &midtermAvg, double weightedGradesAvgOne, 
                    double weightedGradesAvgTwo, double weightedGradeAvgOne)
{
    midtermAvg = weightedGradesAvgOne + weightedGradesAvgTwo + 
                 weightedGradeAvgOne;
}

void displayReportHeading(char collegeName[80], int classSemester)
{
    cout << setprecision(2) << fixed;
    cout << "________________________________________________________________"
            "___" << endl << endl;
    cout << setw(48) << collegeName << endl;
    cout << setw(24) << "Semester " << setw(1) << classSemester 
         << setw(26) << " Student Midterm Grade Report" << endl << endl;
}

void displayStuInputHeading(char className[80], char lastName [80], 
                         char firstName[80])
{
    cout << left << setw(7) << className << right << setw(9) 
         << lastName << setw(2) << ", " << setw(6) << firstName << endl << endl;
    cout << setw(8) << "Category" << setw(17) << "Grade Points" << setw(16)
         << "Max Points" 
         << setw(12) << "Weight" << setw(14) << "Average" 
         << endl;
}



void displayGradesData(char categoryName[80], int gradesOnePoints, 
                       int gradesTwoPoints, int gradesThreePoints,
                       int gradesFourPoints, int gradesFivePoints, 
                       int maxPoints, double categoryWeight, 
                       double unweightedGradesAvg)
{
    cout << left << setw(11) << categoryName << right << setw(3) 
         << gradesOnePoints << setw(1) << "," << setw(2) 
         << gradesTwoPoints << setw(1) << "," << setw(2) << gradesThreePoints 
         << setw(1) << "," << setw(2) << gradesFourPoints << setw(1) << ","
         << setw(2) << gradesFivePoints << setw(11) << maxPoints << setw(15) 
         << categoryWeight << setw(14) << unweightedGradesAvg  
         << "%" << endl << endl;
}

void displayGradeData(char categoryName[80], int gradeOnePoints, int maxPoints, 
                           double categoryWeight, double unweightedGradesAvg)
{
    cout << left << setw(12) << categoryName << right << setw(7) 
         << gradeOnePoints << setw(18) << maxPoints << setw(15) 
         << categoryWeight << setw(14) << unweightedGradesAvg  
         << "%" << endl << endl;
}

void displayMidTermAvg(double midtermAvg)
{
    cout << left << setw(33) << "Midterm" << right 
         << setw(33) << midtermAvg << "%\n\n\n";
    cout << left << setw(15) << "Darwin Castillo" << endl;
    cout << left << setw(7) << "CIS 116" << endl << endl;
}


