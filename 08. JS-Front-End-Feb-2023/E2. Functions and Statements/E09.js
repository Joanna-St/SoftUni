function loadingBar(percent) {
    if (percent < 100) {
        let percentSignCount = percent / 10;
        let dotSignCount = 10 - percentSignCount;
    
        console.log(`${[percent]}% [${'%'.repeat(percentSignCount)}${'.'.repeat(dotSignCount)}]`);
        console.log('Still loading...');
    } else {
        console.log('100% Complete!');
    }
}

loadingBar(0)