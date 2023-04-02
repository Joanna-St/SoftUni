function lockedProfile() {
    let allButtons = document.querySelectorAll('button');

    for (const button of allButtons) {
        button.addEventListener('click', checkInteraction)
    }

    function checkInteraction(e){
        let parentDiv = this.parentElement;
        let locked = parentDiv.querySelectorAll('input[type=radio]')[0];
        let hiddenDiv = parentDiv.children[parentDiv.childElementCount - 2];

        if(!locked.checked){
            if (this.innerText == 'Show more') {
                this.innerText = 'Hide it'
                hiddenDiv.style.display = 'block'
            } else {
                this.innerText = 'Show more'
                hiddenDiv.style.display = 'none'
            }
        }
    }
}