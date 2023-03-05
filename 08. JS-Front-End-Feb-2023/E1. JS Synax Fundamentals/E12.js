function cookingNumbers(num, ...actions) {
    for (const action of actions) {
        switch (action) {
            case 'chop':
                num = num / 2;
                break;
            case 'dice':
                num = Math.sqrt(num);
                break;
            case 'spice':
                num = num + 1;
                break;
            case 'bake':
                num = num * 3;
                break;
            case 'fillet':
                num = num * 0.8;
                break;
        }

        console.log(num.toFixed(1).replace(/\.0/, ''));
    }
}

cookingNumbers('9', 'dice', 'spice', 'chop', 'bake', 'fillet')