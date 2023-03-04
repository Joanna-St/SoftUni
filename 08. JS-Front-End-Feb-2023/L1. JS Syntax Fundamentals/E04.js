function printMonth(num) {
    let defineMonth;
  switch (num) {
    case 1:
        defineMonth = 'January';
      break;
    case 2:
        defineMonth = 'February';
      break;
    case 3:
        defineMonth = 'March';
      break;
    case 4:
        defineMonth = 'April';
      break; 
    case 5:
        defineMonth = 'May';
      break;
    case 6:
        defineMonth = 'June';
      break;
    case 7:
        defineMonth = 'July';
      break;
    case 8:
        defineMonth = 'August';
      break;
    case 9:
        defineMonth = 'September';
      break;
    case 10:
        defineMonth = 'October';
      break;
    case 11:
        defineMonth = 'November';
      break;
    case 12:
        defineMonth = 'December';
      break;
    default:
        defineMonth = 'Error!';
      break;
  }

  console.log(defineMonth);
};

printMonth(22)
