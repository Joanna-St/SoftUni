function addItem() {
    let menu = document.getElementById('menu');
    let newOption = document.createElement('option');

    let textField = document.getElementById('newItemText');
    let valueField = document.getElementById('newItemValue');
    let text = textField.value;
    let value = valueField.value;

    newOption.innerText = text
    newOption.value = value
    menu.appendChild(newOption)
    
    textField.value = null;
    valueField.value = null;
}