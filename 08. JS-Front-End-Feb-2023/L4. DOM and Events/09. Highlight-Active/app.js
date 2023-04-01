function focused() {
    let arr = Array.from(document.querySelectorAll('input'))

    for (const item of arr) {
        item.addEventListener('focus', highlight)
        item.addEventListener('blur', removeHighlight)
    }

    function highlight(e){
        e.currentTarget.parentElement.className = 'focused';
    }

    function removeHighlight(e){
        e.currentTarget.parentElement.className = '';
    }
}