const button = document.querySelector('button');
const form = document.forms.modifyForm;

const activate = (key, value) => {
    form[key].value = value;
    form[key].disabled = key === 'nombre';
}

const updProduct = (nombre, marca, precio) => {

    activate('nombre', nombre)
    activate('precio', precio)
    activate('marca', marca)
    button.disabled = false;
}

const toUpdate = (e) => {

    e.preventDefault();

    const product = {
        nombre: form.nombre.value,
        precio: form.precio.value,
        marca: form.marca.value
    }

    fetch("/Servlets/api", {
        method: 'PUT',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(product)
    })
        .then(res => res.json())
        .then(({ error }) => {
            !error
                ? location.href = '/Servlets'
                : alert('Hubo problemas a la hora de modificar el producto. Intenta otra vez.');
        })

};



button.addEventListener('click', toUpdate);