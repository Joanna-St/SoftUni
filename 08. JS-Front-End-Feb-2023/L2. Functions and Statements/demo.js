function hello() {
    return "Hello, ";
}

function greeting(greetingPart, name) {
    return greetingPart() + name;
}

console.log(greeting(hello, "Jo!"));