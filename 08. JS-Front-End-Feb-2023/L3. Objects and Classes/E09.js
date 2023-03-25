function cats(arr) {
    class Cat{
        constructor(catName, age){
            this.catName = catName;
            this.age = age;
        }

        meow() {
            console.log(`${this.catName}, age ${this.age} says Meow`);
        }
    }

    for (const entry of arr) {
        catName = entry.split(' ')[0];
        age = entry.split(' ')[1]
        let cat = new Cat(catName, age);
        cat.meow();
    }
}

cats(['Candy 1', 'Poppy 3', 'Nyx 2'])