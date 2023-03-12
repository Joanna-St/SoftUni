function formatGrade(grade) {
    let message = '';

    if (grade < 3) {
        message = `Fail (${Math.floor(grade)})`
    } else if (grade >= 3 && grade < 3.5) {
        message = `Poor (${grade.toFixed(2)})`
    } else if (grade >= 3.5 && grade < 4.5) {
        message = `Good (${grade.toFixed(2)})`
    } else if (grade >= 4.5 && grade < 5.5) {
        message = `Very good (${grade.toFixed(2)})`
    } else if (grade >= 5.5) {
        message = `Excellent (${grade.toFixed(2)})`
    }

    console.log(message);
}

formatGrade(5.49)