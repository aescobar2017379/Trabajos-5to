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
            alert("Profesor: V�ctor �lvarez");
        </script>
    </head>
    <body>
        <script>
            var menu = (prompt("Lista de Opciones\n"+
                                "1. Suma\n"+
                                "2. Resta\n"+
                                "3. Divisi�n\n"+
                                "4. Multiplicaci�n\n"+
                                "5. Ra�z"));
        </script>
        
    <script>

        switch(menu){
            case "1":      
                let n1 = parseFloat(prompt("Ingrese el primer n�mero"));
                let n2 = parseFloat(prompt("Ingrese el segundo n�mero"));
                let n3 = parseFloat(prompt("Ingrese el tercer n�mero"));
                let n4 = parseFloat(prompt("Ingrese el cuarto n�mero"));
                let n5 = parseFloat(prompt("Ingrese el quinto n�mero"));
                let suma = n1 + n2 + n3 + n4 + n5;
                document.write("La suma es:" + " "+ suma);
            break;
            case "2":      
                let nr1 = parseFloat(prompt("Ingrese el primer n�mero"));
                let nr2 = parseFloat(prompt("Ingrese el segundo n�mero"));
                let nr3 = parseFloat(prompt("Ingrese el tercer n�mero"));
                let nr4 = parseFloat(prompt("Ingrese el cuarto n�mero"));
                let nr5 = parseFloat(prompt("Ingrese el quinto n�mero"));
                let resta = nr1 - nr2 -nr3 -nr4 - nr5;
                document.write("La resta es:" + " "+ resta);

            break;

            case "3":       
                let nd1 = parseFloat(prompt("Ingrese el primer n�mero"));
                let nd2 = parseFloat(prompt("Ingrese el segundo n�mero"));
                let nd3 = parseFloat(prompt("Ingrese el tercer n�mero"));
                let nd4 = parseFloat(prompt("Ingrese el cuarto n�mero"));
                let nd5 = parseFloat(prompt("Ingrese el quinto n�mero"));
                let division = nd1 / nd2 / nd3 /nd4 / nd5;
                document.write("La divisi�n es:" + " "+ division);
            break;

            case "4":       
                let nm1 = parseFloat(prompt("Ingrese el primer n�mero"));
                let nm2 = parseFloat(prompt("Ingrese el segundo n�mero"));
                let nm3 = parseFloat(prompt("Ingrese el tercer n�mero"));
                let nm4 = parseFloat(prompt("Ingrese el cuarto n�mero"));
                let nm5 = parseFloat(prompt("Ingrese el quinto n�mero"));
                let multiplicacion = nm1 * nm2 * nm3 * nm4 * nm5;
                document.write("La Multiplicaci�n es:" + " "+ multiplicacion);
            break;
            case "5":
                let nra = parseFloat(prompt("Ingrese un n�mero"));
                let raiz = Math.sqrt(nra);
                document.write("La Ra�z es:"+ " "+ raiz);                
            break;
            default:
                alert("Opci�n Inv�lida\n");
                document.write("Refresca la p�gina y elije correctamente");
            break;
            
        }
    </script>

    </body>
</html>
