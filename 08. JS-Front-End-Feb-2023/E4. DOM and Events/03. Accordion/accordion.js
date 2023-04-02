function toggle() {
    let extraDiv = document.getElementById('extra');

    let button = document.getElementsByClassName('button')[0];
    let buttonText = button.textContent;

    if (buttonText == 'More') {
        button.textContent = 'Less'
        extraDiv.style.display = 'block'
    } else {
        button.textContent = 'More'
        extraDiv.style.display = 'none'
    }
}