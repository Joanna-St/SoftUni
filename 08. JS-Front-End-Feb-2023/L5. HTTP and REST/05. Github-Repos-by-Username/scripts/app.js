function loadRepos() {
	const BASE_URL = 'https://api.github.com/users/'
	let userName = document.getElementById('username').value;
	let ul = document.getElementById('repos');
	ul.innerHTML = '';
	fetch(`${BASE_URL}${userName}/repos`)
		.then((res) => res.json())
		.then((arr) => arr.forEach(obj => {
			let li = document.createElement('li');
			let a = document.createElement('a');
			let fullName = obj.full_name;
			let url = obj.html_url;

			a.href = url;
			a.innerText = fullName;
			li.appendChild(a);
			ul.appendChild(li);
		}))
		.catch(() => {
			let li = document.createElement('li');
			let a = document.createElement('a');

			a.href = "{repo.html_url}";
			a.innerText = "{repo.full_name}";
			li.appendChild(a);
			ul.appendChild(li);
		})
}