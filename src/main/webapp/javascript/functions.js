const tbody = (nombre, marca, precio) => {

    const action = getAction();

    if (!action) {
        return listProducts(nombre, marca, precio);
    }

    if (action === 'delete') {
        return deleteProduct(nombre, marca, precio);
    }

    if (action === 'update') {
        return modifyProduct(nombre, marca, precio);
    }

}

const create = (fn, nombre, marca, precio) => {

    const tr = document.createElement('tr');

    tr.innerHTML = nombre && marca && precio
        ? fn(nombre, marca, precio)
        : fn();
    body.append(tr);
}

const empty = () => 'Aún no hay productos</th>'

const alert = (message) => {
    Swal.fire(
        'Ocurrió un error!',
        message,
        'error'
    )
}