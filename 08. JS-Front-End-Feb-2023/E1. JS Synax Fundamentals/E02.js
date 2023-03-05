function calcPrice(num, type, day) {
    let singlePrice = 0;
    switch (type) {
        case "Students":
            switch (day) {
                case "Friday":
                    singlePrice = 8.45;
                    break;
                case "Saturday":
                    singlePrice = 9.80;
                    break;
                case "Sunday":
                    singlePrice = 10.46;
                    break;
            }

            if (num >= 30) {
                singlePrice *= 0.85;
            }
            break;
        case "Business":
            switch (day) {
                case "Friday":
                    singlePrice = 10.90;
                    break;
                case "Saturday":
                    singlePrice = 15.60;
                    break;
                case "Sunday":
                    singlePrice = 16;
                    break;
            }

            if (num >= 100) {
                num -= 10;
            }
            break;
        case "Regular":
            switch (day) {
                case "Friday":
                    singlePrice = 15;
                    break;
                case "Saturday":
                    singlePrice = 20;
                    break;
                case "Sunday":
                    singlePrice = 22.50;
                    break;
            }

            if (num >= 10 && num <= 20) {
                singlePrice *= 0.95;
            }
            break;
    }

    console.log("Total price: " + (singlePrice * num).toFixed(2));
}

calcPrice(40,
    "Regular",
    "Saturday")