function calcArea(r) {
  let type = typeof r;
  switch (type) {
    case "number":
      console.log((Math.PI * r * r).toFixed(2));
      break;

    default:
      console.log(
        `We can not calculate the circle area, because we receive a ${type}.`
      );
      break;
  }
}

calcArea(null)