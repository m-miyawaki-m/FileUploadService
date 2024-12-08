$(document).ready(function () {
    function fetchResults(page = 1) {
        // 検索結果の取得処理
        const formData = {
            title: $('#title').val(),
            fileName: $('#fileName').val(),
            uploadDateFrom: $('#uploadDateFrom').val(),
            uploadDateTo: $('#uploadDateTo').val(),
            page: page
        };

        $.ajax({
            url: '/api/files/search',
            method: 'GET',
            data: formData,
            success: function (response) {
                const results = response.content; // ページングされた結果
                const tbody = $('#resultTable tbody');
                tbody.empty(); // 古い結果をクリア

                results.forEach(result => {
                    tbody.append(`
                        <tr>
                            <td><input type="checkbox" class="selectCheckbox" data-id="${result.id}"></td>
                            <td>${result.title}</td>
                            <td>${result.fileName}</td>
                            <td>${result.uploadDate}</td>
                        </tr>
                    `);
                });

                // チェックボックスの状態を確認して削除ボタンを有効化
                bindCheckboxEvents();
            },
            error: function (err) {
                console.error('Error fetching search results:', err);
                alert('Failed to fetch search results. Please try again.');
            }
        });
    }

    function bindCheckboxEvents() {
        $('.selectCheckbox').on('change', function () {
            const selected = $('.selectCheckbox:checked');
            $('#deleteButton').prop('disabled', selected.length === 0);
        });
    }

    // 削除ボタンのクリックイベント
    $('#deleteButton').on('click', function () {
        const selectedCheckbox = $('.selectCheckbox:checked');
        if (selectedCheckbox.length === 0) {
            alert('Please select a file to delete.');
            return;
        }
        if (selectedCheckbox.length > 1) {
            alert('Please select only one file to delete.');
            return;
        }

        // 選択された行の情報を取得
        const row = selectedCheckbox.closest('tr');
        const fileId = selectedCheckbox.data('id');
        const fileTitle = row.find('td:nth-child(2)').text();
        const fileName = row.find('td:nth-child(3)').text();
        const uploadDate = row.find('td:nth-child(4)').text();

        // 削除確認ページに遷移
        const queryParams = new URLSearchParams({
            id: fileId,
            title: fileTitle,
            fileName: fileName,
            uploadDate: uploadDate
        }).toString();
        window.location.href = `/file/deleteConfirmation?${queryParams}`;
    });

    // 検索ボタンのクリックイベント
    $('#searchButton').click(() => fetchResults());

    // リセットボタンのクリックイベント
    $('#resetButton').click(() => {
        $('#searchForm')[0].reset();
        fetchResults();
    });

    // 初期ロード
    fetchResults();
});
