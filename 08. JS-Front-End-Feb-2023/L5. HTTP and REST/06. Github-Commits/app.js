// async function loadCommits() {
//     const BASE_URL = 'https://api.github.com/repos/';
//     const userName = document.getElementById('username').value;
//     const repo = document.getElementById('repo').value;
//     let ul = document.getElementById('commits');
//     ul.innerHTML = '';
//     let resStatus = '';

//     try {
//         const response = await fetch(`${BASE_URL}${userName}/${repo}/commits`);
//         let status = response.status;

//         if (status >= 400) {
//             resStatus = status;
//         }

//         for (const { commit } of await response.json()) {
//             let li = document.createElement('li');
//             let author = commit.author.name;
//             let message = commit.message;

//             li.innerText = `${author}: ${message}`;
//             ul.appendChild(li);
//         }
//     } catch (err) {
//         let li = document.createElement('li');
//         li.innerText = `Error: ${resStatus} (Not Found)`;
//         ul.appendChild(li);
//     }
// }

function loadCommits() {
    const BASE_URL = 'https://api.github.com/repos/';
    const userName = document.getElementById('username').value;
    const repo = document.getElementById('repo').value;
    let ul = document.getElementById('commits');
    ul.innerHTML = '';
    let resStatus = '';

    fetch(`${BASE_URL}${userName}/${repo}/commits`)
        .then(response => {
            resStatus = response.status;
            return response.json();
        })
        .then(arr => {
            for (const obj of arr) {
                let li = document.createElement('li');
                let author = obj.commit.author.name;
                let message = obj.commit.message;
                li.innerText = `${author}: ${message}`;
                ul.appendChild(li);
            }
        })
        .catch(err => {
            let li = document.createElement('li');
            li.innerText = `Error: ${resStatus} (Not Found)`;
            ul.appendChild(li);
        })
}