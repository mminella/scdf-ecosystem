kind create cluster --config=kind-config.yaml
kubectl cluster-info --context kind-kind

kubectl create -f rabbitmq/
kubectl create -f mysql/

kubectl create -f wavefront-secret.yaml

kubectl create -f server/server-roles.yaml
kubectl create -f server/server-rolebinding.yaml
kubectl create -f server/service-account.yaml

kubectl create -f skipper/skipper-config-rabbit.yaml
kubectl create -f skipper/skipper-deployment.yaml
kubectl create -f skipper/skipper-svc.yaml

kubectl create -f server/server-config.yaml
kubectl create -f server/server-svc.yaml
kubectl create -f server/server-deployment.yaml
