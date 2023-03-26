function heroInventory(arr) {
    let heroRegister = {};

    for (const entry of arr) {
        let heroInfo = entry.split(' / ')
        let heroName = heroInfo[0];
        let heroLevel = Number(heroInfo[1]);
        let heroItems = heroInfo[2].split(', ')

        let hero = {
            heroName,
            heroLevel,
            heroItems,

            printInfo() {
                console.log(`Hero: ${heroName}\n`+
                `level => ${heroLevel}\n` +
                `items => ${heroItems.join(', ')}`);
            }
        }

        heroRegister[heroName] = hero;
    }

    Object.values(heroRegister).sort((hero1, hero2) => hero1.heroLevel - hero2.heroLevel).forEach(hero => hero.printInfo());
}

heroInventory([
    'Batman / 2 / Banana, Gun',
    'Superman / 18 / Sword',
    'Poppy / 28 / '
    ])