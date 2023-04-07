function attachEvents() {
    const POSTS_URL = 'http://localhost:3030/jsonstore/blog/posts/';
    const COMMENTS_URL = 'http://localhost:3030/jsonstore/blog/comments/'
    let loadBtn = document.getElementById('btnLoadPosts');
    loadBtn.addEventListener('click',listLoader);
    let viewBtn = document.getElementById('btnViewPost');
    viewBtn.addEventListener('click',postLoader)
    let allPosts = [];
    let allComments = [];
    let dropDownMenu = document.getElementById('posts');
    let postTitle = document.getElementById('post-title');
    let postBody = document.getElementById('post-body');
    let commentsUL = document.getElementById('post-comments');

    async function listLoader(){
        let loadRes = await fetch(POSTS_URL);
        let postsObj = await loadRes.json();
        allPosts = Object.values(postsObj);

        for (const post of allPosts) {
            let newOption = document.createElement('option')
            newOption.value = post.id;
            newOption.textContent = post.title;

            dropDownMenu.appendChild(newOption)
        }
    }

    async function postLoader() {
        postTitle.innerText = '';
        postBody.innerText = '';
        commentsUL.innerHTML = '';

        let allOptions = Array.of(...dropDownMenu.children);
        let selectedOption = allOptions.filter(option => option.selected == true)[0];
        let selectedID = selectedOption.value;

        let viewRes = await fetch(`${POSTS_URL}${selectedID}`);
        let postObj = await viewRes.json();
        
        postTitle.innerText = postObj.title;
        postBody.innerText = postObj.body;

        let loadRes = await fetch(COMMENTS_URL);
        let commentsObj = await loadRes.json();
        allComments = Object.values(commentsObj);

        let postComments = allComments.filter(comment => comment.postId == selectedID);

        for (const comment of postComments) {
            let newLi = document.createElement('li');
            newLi.innerText = comment.text;

            commentsUL.appendChild(newLi)
        }
    }
}

attachEvents();