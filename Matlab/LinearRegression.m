function [ W,phi ] = LinearRegression( X,Y,order )
%This function is computing a linear regression using the normal equations.
%INPUT: 
%   - X: matrix of input data
%   - Y=Y(X) output array
%   - order of the regression
%OUTPUT:
%   -W
%   -phi:

phi=[ones(size(X,1),1),repmat(X,1,order)...
    .^(repmat(reshape(repmat(1:order,size(X,2),1),1,size(X,2)*(order)),size(X,1),1))];


W=(phi'*phi)^(-1) * phi'* Y;

end

