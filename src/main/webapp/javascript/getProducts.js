fetch("/ensamble/api")
    .then(res => res.json())
    .then(data => {
        if (data.length > 0) {
            data.map(producto => {
                const { nombre, marca, stock } = producto;
                create(tbody, nombre, marca, stock);
            })
            foot.innerHTML = data.length;
        }
        else {
            create(empty)
            foot.innerHTML = 0;
        }
    })