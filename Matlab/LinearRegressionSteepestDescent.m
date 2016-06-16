function [ W,phi ] = LinearRegressionSteepestDescent( X,Y,order,eta,nbiter )
%UNTITLED5 Summary of this function goes here
%INPUT
%   -eta: learning rate parameter

W=zeros(1+size(X,2)*order,1);
phi=[ones(size(X,1),1),repmat(X,1,order)...
    .^(repmat(reshape(repmat(1:order,size(X,2),1),1,size(X,2)*(order)),size(X,1),1))];

diffE_prev=0;
for iter=1:nbiter
    diffE=phi'*(Y-phi*W);
    W=W+eta.*diffE;
    if (abs(diffE_prev-diffE)>0) 
        eta=eta/2;   
    else
        eta=eta*2;
    end
    diffE_prev=diffE;

end

end

