function deleteByEmail() {
    let rowsArr = Array.from(document.querySelector('tbody').children);
    let mailToDelete = document.querySelector('label > input').value;
    let rowToDelete = null;

    for (const row of rowsArr) {
        let mail = row.children[1].innerText;

        if (mail == mailToDelete) {
            rowToDelete = row;
        }
    }

    if (rowToDelete != null) {
        rowToDelete.remove()
        document.getElementById('result').innerText = 'Deleted.'
    } else {
        document.getElementById('result').innerText = 'Not found.'
    }
}