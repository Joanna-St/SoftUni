function carWash(commands) {
    const soap = (x) => x + 10;
    const water = (x) => x * 1.2;
    const vacuum = (x) => x * 1.25;
    const mud = (x) => x * 0.9;

    const comandsMap = {
        soap,
        water,
        'vacuum cleaner': vacuum,
        mud
    }

    let cleanState = 0;

    for (const command of commands) {
        cleanState = comandsMap[command](cleanState);
    }

    console.log(`The car is ${cleanState.toFixed(2)}% clean.`);
}

carWash(["soap", "water", "mud", "mud", "water", "mud", "vacuum cleaner"])