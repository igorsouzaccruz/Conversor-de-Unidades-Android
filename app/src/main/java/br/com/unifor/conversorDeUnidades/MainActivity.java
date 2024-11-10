    package br.com.unifor.conversorDeUnidades;

    import android.annotation.SuppressLint;
    import android.os.Bundle;
    import android.view.View;

    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Spinner;
    import android.widget.TextView;
    import androidx.appcompat.app.AppCompatActivity;

    import java.text.DecimalFormat;

    public class MainActivity extends AppCompatActivity {

        private EditText editTextValorDeEntrada;
        private Spinner spinnerOriginal, spinnerFinal;
        private TextView textViewResultado;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            editTextValorDeEntrada = findViewById(R.id.valorDeEntrada);
            spinnerOriginal = findViewById(R.id.spinner_original);
            spinnerFinal = findViewById(R.id.spinner_final);
            textViewResultado = findViewById(R.id.textResultado);
            Button btnConverter = findViewById(R.id.btn_converter);

            String[] unidades = {"Centímetros", "Metros", "Quilômetros", "Milhas"};

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unidades);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerOriginal.setAdapter(adapter);
            spinnerFinal.setAdapter(adapter);

            btnConverter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    converterUnidades();
                }
            });
        }

        private void converterUnidades() {
        double valorDeEntrada = Double.parseDouble(editTextValorDeEntrada.getText().toString());
        String unidadeOriginal = spinnerOriginal.getSelectedItem().toString();
        String unidadeFinal = spinnerFinal.getSelectedItem().toString();
        double resultado = 0;

        if(unidadeOriginal.equals(unidadeFinal)){
            resultado = valorDeEntrada;
        }
        switch (unidadeOriginal) {
            case "Centímetros":
                resultado = converterParaCentimetros(valorDeEntrada, unidadeFinal);
                break;
            case "Metros":
                resultado = converterParaMetros(valorDeEntrada, unidadeFinal);
                break;
            case "Quilômetros":
                resultado = converterParaQuilometros(valorDeEntrada, unidadeFinal);
                break;
            case "Milhas":
                resultado = converterParaMilhas(valorDeEntrada, unidadeFinal);
                break;
        }

        imprimirResultado(resultado);
        }

        @SuppressLint("DefaultLocale")
        private void imprimirResultado(double resultado) {
            DecimalFormat formatoNormal = new DecimalFormat("0.######");
            DecimalFormat formatoCientifico = new DecimalFormat("0.#####E0");


            if (Math.abs(resultado) < 0.0001 || Math.abs(resultado) > 100000) {
                textViewResultado.setText(formatoCientifico.format(resultado));
            } else {
                textViewResultado.setText(formatoNormal.format(resultado));
            }
        }

        private double converterParaCentimetros(double value, String unidadeFinal) {
            switch (unidadeFinal) {
                case "Metros":
                    return value / 100;
                case "Quilômetros":
                    return value / 100000;
                case "Milhas":
                    return value / 160934;
                default:
                    return value;
            }
        }

        private double converterParaMetros(double value, String unidadeFinal) {
            switch (unidadeFinal) {
                case "Centímetros":
                    return value * 100;
                case "Quilômetros":
                    return value / 1000;
                case "Milhas":
                    return value * 0.000621371;
                default:
                    return value;
            }
        }

        private double converterParaQuilometros(double value, String unidadeFinal) {
            switch (unidadeFinal) {
                case "Centímetros":
                    return value * 100000;
                case "Metros":
                    return value * 1000;
                case "Milhas":
                    return value * 0.621371;
                default:
                    return value;
            }
        }

        private double converterParaMilhas(double value, String unidadeFinal) {
            switch (unidadeFinal) {
                case "Centímetros":
                    return value * 160934;
                case "Metros":
                    return value * 1609.34;
                case "Quilômetros":
                    return value * 1.60934;
                default:
                    return value;
            }
        }
    }

