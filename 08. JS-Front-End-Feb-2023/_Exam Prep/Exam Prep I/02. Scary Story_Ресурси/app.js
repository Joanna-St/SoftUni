window.addEventListener("load", solve);

function solve() {
  let publishBtn = document.getElementById('form-btn');
  publishBtn.addEventListener('click', previewHandler);
  let optionsCollection = document.getElementById('genre').children;
  let optionsArr = Array.from(optionsCollection)
  let previewList = document.getElementById('preview-list')
  let mainDiv = document.getElementById('main');

  function previewHandler() {
    previewList.innerHTML = '<h3>Preview</h3>'
    let firstNameInput = document.getElementById('first-name');
    let lastNameInput = document.getElementById('last-name');
    let ageInput = document.getElementById('age');
    let titleInput = document.getElementById('story-title');
    let storyInput = document.getElementById('story');

    let firstName = firstNameInput.value;
    let lastName = lastNameInput.value;
    let age = ageInput.value;
    let title = titleInput.value;
    let story = storyInput.value;
    let genre = optionsArr.filter(option => option.selected == true)[0].textContent;

    if (firstName !== '' && lastName !== '' && age !== '' && title !== '' && story !== '' && genre !== '') {
      let newLi = document.createElement('li');
      newLi.className = 'story-info';
      let newArticle = document.createElement('article');
      let nameHeader = document.createElement('h4');
      nameHeader.textContent = `Name: ${firstName} ${lastName}`;
      newArticle.appendChild(nameHeader);
      let agePar = document.createElement('p');
      agePar.textContent = `Age: ${age}`;
      newArticle.appendChild(agePar);
      let titlePar = document.createElement('p');
      titlePar.textContent = `Title: ${title}`;
      newArticle.appendChild(titlePar);
      let genrePar = document.createElement('p');
      genrePar.textContent = `Genre: ${genre}`;
      newArticle.appendChild(genrePar);
      let storyPar = document.createElement('p');
      storyPar.textContent = story;
      newArticle.appendChild(storyPar);
      newLi.appendChild(newArticle);

      let saveBtn = document.createElement('button');
      saveBtn.className = 'save-btn'
      saveBtn.textContent = 'Save Story'
      saveBtn.addEventListener('click', saveHandler);
      newLi.appendChild(saveBtn)
      let editBtn = document.createElement('button');
      editBtn.className = 'edit-btn'
      editBtn.textContent = 'Edit Story'
      editBtn.addEventListener('click', editHandler);
      newLi.appendChild(editBtn)
      let deleteBtn = document.createElement('button');
      deleteBtn.className = 'delete-btn'
      deleteBtn.textContent = 'Delete Story'
      deleteBtn.addEventListener('click', deleteHandler)
      newLi.appendChild(deleteBtn)

      previewList.appendChild(newLi)

      firstNameInput.value = '';
      lastNameInput.value = '';
      ageInput.value = '';
      titleInput.value = '';
      storyInput.value = '';
      publishBtn.disabled = true
    }


    function editHandler() {
      firstNameInput.value = firstName;
      lastNameInput.value = lastName;
      ageInput.value = age;
      titleInput.value = title;
      storyInput.value = story;
      publishBtn.disabled = '';

      previewList.innerHTML = '<h3>Preview</h3>'
    }

    function deleteHandler() {
      previewList.innerHTML = '<h3>Preview</h3>'
      publishBtn.disabled = '';
    }

    function saveHandler() {
      mainDiv.innerHTML = '<h1>Your scary story is saved!</h1>'
    }
  }

}
