function isExcellent(grade) {
    let checkGrade = 'Excellent';
    if (grade < 5.50) {
        checkGrade = 'Not excellent';
    };

    console.log(checkGrade);
};

isExcellent(5.5);