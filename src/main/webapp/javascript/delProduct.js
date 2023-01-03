const delProduct = (name) => {

    fetch(`/Servlets/api?nombre=${name}`, { method: 'DELETE' })
        .then(res => res.json())
        .then(({ error }) => {
            !error
                ? location.href = '/Servlets'
                : alert('Hubo problemas a la hora de borrar el producto. Intenta otra vez.');
        });
};

