function createMedicalChart(name, age, weight, height) {
	let personalInfo = {
		age: age,
		weight: weight,
		height: height
	}

	let BMI = weight / (height * height);

	let medicalChart = {
		name: name,
		personalInfo: personalInfo,
		BMI: BMI
	}

	switch (true) {
		case BMI < 18.5:
			medicalChart['status'] = "underweight";
			break;
		case BMI < 25:
			medicalChart['status'] = "normal";
			break;
		case BMI < 30:
			medicalChart['status'] = "overweight";
			break;
		default:
			medicalChart['status'] = "obese";
			medicalChart[recommendation] = "admission required";
			break;
	}

	console.log(JSON.stringify(medicalChart, null, 2));
}

createMedicalChart("Peter", 29, 75, 182)