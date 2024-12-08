async function uploadFile() {
    const form = document.getElementById('fileUploadForm');
    const formData = new FormData(form);

    try {
        const response = await fetch('http://localhost:8080/api/files/upload', {
            method: 'POST',
            body: formData,
        });

        if (response.ok) {
            const result = await response.text();
            document.getElementById('response').innerText = `Success: ${result}`;
        } else {
            const error = await response.text();
            document.getElementById('response').innerText = `Error: ${error}`;
        }
    } catch (error) {
        document.getElementById('response').innerText = `Request failed: ${error}`;
    }
}
