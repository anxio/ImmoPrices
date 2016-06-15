function [  ] = plotArea( string )

M = csvread(strcat(string,'.csv'),1,0);
M(:,[1 2])=M(:,[1 2]).*10^-6;

figure
    subplot(311)
    hold on
    ylim([0 max(M(:,1))])
    xlim([0 max(M(:,3))])
    xlabel('Surface (m^2)')
    ylabel('Asked price (million kr) ')
    s=size(M,1);    
    for k=1:s
        if (M(k,3)~=0)
        plot(M(k,3),M(k,1),'o');      
        
        end
    end

subplot(312)
    hold on
    ylim([0 max(M(:,2))])
    xlim([0 max(M(:,3))])
    xlabel('Surface (m^2)')
    ylabel('Final price (million kr) ')

for k=1:s
    if (M(k,3)~=0)
        plot(M(k,3),M(k,2),'o');      
        
    end
end

subplot(313)

hold on
    ylim([min(M(:,2)-M(:,1)) max(M(:,2)-M(:,1))])
    xlim([0 max(M(:,3))])
    xlabel('Surface (m^2)')
    ylabel('The difference between final and asked price (million kr) ')

for k=1:s
    if (M(k,3)~=0)
        plot(M(k,3),(M(k,2)-M(k,1)),'o');      
    end
end


end


