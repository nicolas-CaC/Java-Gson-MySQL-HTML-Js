const getAction = () => {
    const fullpath = location.href.split('/');
    const path = fullpath.at(-1);
    const action = path.split('=').at(-1);
    return action;
}

const listProducts = (nombre, marca, precio) =>
    `<td>${nombre}</td>
    <td>${marca}</td>
    <td>${precio}</td>`

const modifyProduct = (nombre, marca, precio) =>
    `<td>
        <button class="button" onclick="updProduct('${nombre}','${marca}','${precio}')">${nombre}</button>
    </td>
    <td>${marca}</td>
    <td>${precio}</td>`


const deleteProduct = (nombre, marca, precio) =>
    `<td>
        <button class="button" onclick="delProduct('${nombre}')">${nombre}</button>
    </td>
    <td>${marca}</td>
    <td>${precio}</td>`

