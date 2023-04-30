<!DOCTYPE html>
<html>
    <head>
        <title>Calculadora</title>
        <style>
            body{
                background-image: linear-gradient(180deg, #6dc918b2 0, #a5ff2fc0 16.67%, #2dff6cc7 33.33%, #06f0aa 50%, #0af0d193 66.67%, #07dffcaf 83.33%, #07dffcaf 100%);
                text-align: center;
                font-family: Century725 Cn BT;
                padding: 10%;
                font-size: 60px;
                border: 6px solid #ffffff;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            alert("Hola Mundo");
            alert("Angel Gabriel Escobar Arevalo");
            alert("2017379");
            alert("IN5CV");
            alert("Profesor: Víctor Álvarez");
        </script>
    </head>
    <body>
        <script>
            var menu = (prompt("Lista de Opciones\n"+
                                "1. Suma\n"+
                                "2. Resta\n"+
                                "3. División\n"+
                                "4. Multiplicación\n"+
                                "5. Raíz"));
        </script>
        
    <script>

        switch(menu){
            case "1":      
                let n1 = parseFloat(prompt("Ingrese el primer número"));
                let n2 = parseFloat(prompt("Ingrese el segundo número"));
                let n3 = parseFloat(prompt("Ingrese el tercer número"));
                let n4 = parseFloat(prompt("Ingrese el cuarto número"));
                let n5 = parseFloat(prompt("Ingrese el quinto número"));
                let suma = n1 + n2 + n3 + n4 + n5;
                document.write("La suma es:" + " "+ suma);
            break;
            case "2":      
                let nr1 = parseFloat(prompt("Ingrese el primer número"));
                let nr2 = parseFloat(prompt("Ingrese el segundo número"));
                let nr3 = parseFloat(prompt("Ingrese el tercer número"));
                let nr4 = parseFloat(prompt("Ingrese el cuarto número"));
                let nr5 = parseFloat(prompt("Ingrese el quinto número"));
                let resta = nr1 - nr2 -nr3 -nr4 - nr5;
                document.write("La resta es:" + " "+ resta);

            break;

            case "3":       
                let nd1 = parseFloat(prompt("Ingrese el primer número"));
                let nd2 = parseFloat(prompt("Ingrese el segundo número"));
                let nd3 = parseFloat(prompt("Ingrese el tercer número"));
                let nd4 = parseFloat(prompt("Ingrese el cuarto número"));
                let nd5 = parseFloat(prompt("Ingrese el quinto número"));
                let division = nd1 / nd2 / nd3 /nd4 / nd5;
                document.write("La división es:" + " "+ division);
            break;

            case "4":       
                let nm1 = parseFloat(prompt("Ingrese el primer número"));
                let nm2 = parseFloat(prompt("Ingrese el segundo número"));
                let nm3 = parseFloat(prompt("Ingrese el tercer número"));
                let nm4 = parseFloat(prompt("Ingrese el cuarto número"));
                let nm5 = parseFloat(prompt("Ingrese el quinto número"));
                let multiplicacion = nm1 * nm2 * nm3 * nm4 * nm5;
                document.write("La Multiplicación es:" + " "+ multiplicacion);
            break;
            case "5":
                let nra = parseFloat(prompt("Ingrese un número"));
                let raiz = Math.sqrt(nra);
                document.write("La Raíz es:"+ " "+ raiz);                
            break;
            default:
                alert("Opción Inválida\n");
                document.write("Refresca la página y elije correctamente");
            break;
            
        }
    </script>

    </body>
</html>
