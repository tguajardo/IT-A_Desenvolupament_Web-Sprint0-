const cells = document.querySelectorAll('.board-cell');

cells.forEach(cell => {

    cell.addEventListener('click', function() {
        const form = document.createElement('form');

        form.method = 'POST';

        form.action ="/";

        form.innerHTML = `
            <input type="hidden" name="row" value="${cell.dataset.row}">
            <input type="hidden" name="column" value="${cell.dataset.column}">
        `;

        document.body.appendChild(form);
        form.submit();    
    });

});