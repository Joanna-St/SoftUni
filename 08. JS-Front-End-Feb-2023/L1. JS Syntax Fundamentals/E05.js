function calc(a, b, operator) {
  let result;

  switch (operator) {
    case "+":
      result = a + b;
      break;
    case "-":
      result = a - b;
      break;
    case "*":
      result = a * b;
      break;
    case "/":
      result = a / b;
      break;
    case "%":
      result = a % b;
      break;
    case "**":
      result = a ** b;
      break;

    default:
        result = 'Error!'
      break;
  }

  console.log(result);
}

calc(3, 5.5, '*')
