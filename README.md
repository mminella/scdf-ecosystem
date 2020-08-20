# Data Processing with Spring

This code repository is the code and deck for my Data Processing with Spring presentation.

To run the examples as I do in the talk, you will need [kind](https://kind.sigs.k8s.io/) and [k9s](https://github.com/derailed/k9s) installed locally on your machine. You can run this on other Kubernetes installations, however the volume mount configurations will differ.

## Environment setup

With `kind` and `k9s` installed on your machine and no kind cluster running, the other thing you will need to do is to sign up for a Wavefront account. You can do that for free [here](https://www.wavefront.com/). Once registered, you will need your API key and URL.

1. In the root of the `config` directory, create a file called `wavefront-secret.yaml` with the following yaml in it replacing the `<YOUR_API_KEY_HERE>` with your Wavefront API key base64 encoded:  

    ```yaml
    apiVersion: v1
    kind: Secret
    metadata:
      name: wavefront-api
      labels:
        app: wavefront
    data:
      wavefront-api-token: <YOUR_API_KEY_HERE>
    ```  

2. In the `kind-config.yaml` change the value for the `hostPath` on line 7 to match the location you will put the input directory on your host system.
3. From the root of the `config` directory, run the `deploy.sh` script.
4. Run `k9s`.
5. Using `k9s` select the mysql pod and press `s` to ssh into the pod.
6. Launch mysql via `mysql -u root -p` providing the password (`yourpassword` if you have not changed it in the yaml).
7. Choose the `mysql` database by running `use mysql;`.
8. Create the `TPS_REPORTS` table by running the create statement:

    ```sql
    CREATE TABLE TPS_REPORTS  (
        ID BIGINT NOT NULL AUTO_INCREMENT ,
        COVERSHEET VARCHAR(100) NOT NULL,
        REPORT VARCHAR(100) NOT NULL,
        PRIMARY KEY (ID)
    ) ENGINE=InnoDB;
    ```
9. Type `exit;` to leave the MySql shell and `exit` again to leave the pod.
10. Using `k9s` select the `scdf-server` pod and press `<shift>-f` to add an ingress port. In my demo I used port 9393.
11. In a browser navigate to `http://localhost:9393/dashboard` to find the SCDF dashboard.

